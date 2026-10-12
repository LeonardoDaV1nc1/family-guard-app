package com.familysafety.service;

import com.familysafety.dto.ActivityReportDTO;
import com.familysafety.model.ActivityLog;
import com.familysafety.model.ContentRule;
import com.familysafety.repository.ActivityLogRepository;
import com.familysafety.repository.ContentRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private ContentRuleRepository contentRuleRepository;

    @Autowired
    private AiService aiService; 

    private static final List<String> GLOBAL_BLOCKED_KEYWORDS = List.of("adult", "gambling", "bet", "violence");

    public ActivityLog logActivity(Long childId, String contentType, String contentValue, Long parentId) {
        String rating = "SUITABLE";
        String lowerValue = contentValue.toLowerCase();

        if (parentId != null) {
            List<ContentRule> rules = contentRuleRepository.findByParentIdAndActiveTrue(parentId);
            for (ContentRule rule : rules) {
                if (lowerValue.contains(rule.getKeyword())) {
                    rating = "INAPPROPRIATE";
                    break;
                }
            }
        }

        if ("SUITABLE".equals(rating)) {
            for (String keyword : GLOBAL_BLOCKED_KEYWORDS) {
                if (lowerValue.contains(keyword)) {
                    rating = "INAPPROPRIATE";
                    break;
                }
            }
        }

        String category = aiService.classifyApp(contentValue);
        ActivityLog log = new ActivityLog();
        log.setChildId(childId);
        log.setAppName(contentValue); 
        log.setCategory(category);

        return activityLogRepository.save(log);
    }

    public List<ActivityLog> getActivitiesByChild(Long childId) {
        return activityLogRepository.findByChildIdOrderByTimestampDesc(childId);
    }

    public ActivityReportDTO getChildReport(Long childId) {
        Long total = activityLogRepository.countByChildId(childId);
        Long suitable = activityLogRepository.countByChildIdAndRating(childId, "SUITABLE");
        Long inappropriate = activityLogRepository.countByChildIdAndRating(childId, "INAPPROPRIATE");

        return new ActivityReportDTO(total, suitable, inappropriate);
    }
}