import 'package:flutter/material.dart';

class AlertasBloqueioScreen extends StatelessWidget {
  const AlertasBloqueioScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F6),
      appBar: AppBar(
        title: const Text('Alertas de Bloqueio', style: TextStyle(color: Color(0xFF20352F), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFF20352F)),
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(20),
          children: [
            _buildBlockCard(
              'Tentativa de Acesso Web Restrito',
              'O site "site-nao-seguro.com" foi bloqueado por conter filtros de risco elevado.',
              'Hoje às 11:30',
              Icons.web_asset_off,
            ),
            const SizedBox(height: 12),
            _buildBlockCard(
              'Aplicação Restrita Bloqueada',
              'Abertura do app "TikTok" impedida pelo horário de estudo configurado.',
              'Ontem às 15:00',
              Icons.block,
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildBlockCard(String titulo, String detalhe, String dataHora, IconData icon) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: const Color(0xFFFCA5A5)),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            padding: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              color: const Color(0xFFFEE2E2),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Icon(icon, color: const Color(0xFFB5443D), size: 22),
          ),
          const SizedBox(width: 14),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(titulo, style: const TextStyle(fontSize: 14, fontWeight: FontWeight.bold, color: Color(0xFF20352F))),
                const SizedBox(height: 4),
                Text(detalhe, style: const TextStyle(fontSize: 12, color: Color(0xFF6A7B75))),
                const SizedBox(height: 8),
                Text(dataHora, style: const TextStyle(fontSize: 11, color: Color(0xFFB5443D), fontWeight: FontWeight.w600)),
              ],
            ),
          ),
        ],
      ),
    );
  }
}