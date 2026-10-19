import 'package:flutter/material.dart';

class AplicacoesInstaladasScreen extends StatefulWidget {
  const AplicacoesInstaladasScreen({super.key});

  @override
  State<AplicacoesInstaladasScreen> createState() => _AplicacoesInstaladasScreenState();
}

class _AplicacoesInstaladasScreenState extends State<AplicacoesInstaladasScreen> {
  final List<Map<String, dynamic>> _apps = [
    {'nome': 'YouTube', 'categoria': 'Entretenimento', 'bloqueado': false, 'icone': Icons.play_circle_fill, 'cor': Color(0xFFEF4444)},
    {'nome': 'TikTok', 'categoria': 'Rede Social', 'bloqueado': true, 'icone': Icons.video_library, 'cor': Color(0xFF000000)},
    {'nome': 'Roblox', 'categoria': 'Jogos', 'bloqueado': false, 'icone': Icons.sports_esports, 'cor': Color(0xFF2563EB)},
    {'nome': 'Discord', 'categoria': 'Comunicação', 'bloqueado': true, 'icone': Icons.chat_bubble, 'cor': Color(0xFF5865F2)},
    {'nome': 'WhatsApp', 'categoria': 'Comunicação', 'bloqueado': false, 'icone': Icons.message, 'cor': Color(0xFF22C55E)},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F6),
      appBar: AppBar(
        title: const Text('Aplicações Instaladas', style: TextStyle(color: Color(0xFF20352F), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFF20352F)),
      ),
      body: SafeArea(
        child: ListView.separated(
          padding: const EdgeInsets.all(20),
          itemCount: _apps.length,
          separatorBuilder: (context, index) => const SizedBox(height: 12),
          itemBuilder: (context, index) {
            final app = _apps[index];
            return Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: const Color(0xFFE2E9E5)),
              ),
              child: Row(
                children: [
                  Container(
                    padding: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: (app['cor'] as Color).withOpacity(0.1),
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: Icon(app['icone'] as IconData, color: app['cor'] as Color, size: 24),
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          app['nome'],
                          style: const TextStyle(fontSize: 15, fontWeight: FontWeight.bold, color: Color(0xFF20352F)),
                        ),
                        Text(
                          app['categoria'],
                          style: const TextStyle(fontSize: 12, color: Color(0xFF6A7B75)),
                        ),
                      ],
                    ),
                  ),
                  Switch(
                    value: !app['bloqueado'],
                    activeColor: const Color(0xFF246B5D),
                    onChanged: (permitido) {
                      setState(() {
                        _apps[index]['bloqueado'] = !permitido;
                      });
                    },
                  ),
                ],
              ),
            );
          },
        ),
      ),
    );
  }
}