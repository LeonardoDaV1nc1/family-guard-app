import 'package:flutter/material.dart';

class HistoricoConteudosScreen extends StatelessWidget {
  const HistoricoConteudosScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F6),
      appBar: AppBar(
        title: const Text('Histórico de Conteúdos', style: TextStyle(color: Color(0xFF20352F), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFF20352F)),
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(20),
          children: [
            const Text(
              'HOJE',
              style: TextStyle(fontSize: 11, fontWeight: FontWeight.bold, color: Color(0xFF6A7B75), letterSpacing: 0.8),
            ),
            const SizedBox(height: 12),
            _buildHistoryItem('Pesquisa: "Trabalho de Geografia"', 'Navegador Web', '14:20', Icons.search, const Color(0xFF246B5D)),
            const SizedBox(height: 8),
            _buildHistoryItem('Vídeo: "Documentário sobre Oceanos"', 'YouTube', '13:45', Icons.play_arrow_rounded, const Color(0xFFEF4444)),
            const SizedBox(height: 20),
            const Text(
              'ONTEM',
              style: TextStyle(fontSize: 11, fontWeight: FontWeight.bold, color: Color(0xFF6A7B75), letterSpacing: 0.8),
            ),
            const SizedBox(height: 12),
            _buildHistoryItem('Acesso: Wikipedia - História do Brasil', 'Navegador Web', '18:10', Icons.public, const Color(0xFF2563EB)),
          ],
        ),
      ),
    );
  }

  Widget _buildHistoryItem(String titulo, String origem, String hora, IconData icon, Color color) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: const Color(0xFFE2E9E5)),
      ),
      child: Row(
        children: [
          Icon(icon, color: color, size: 22),
          const SizedBox(width: 14),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(titulo, style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w600, color: Color(0xFF20352F))),
                const SizedBox(height: 2),
                Text(origem, style: const TextStyle(fontSize: 12, color: Color(0xFF6A7B75))),
              ],
            ),
          ),
          Text(hora, style: const TextStyle(fontSize: 11, color: Color(0xFF6A7B75))),
        ],
      ),
    );
  }
}