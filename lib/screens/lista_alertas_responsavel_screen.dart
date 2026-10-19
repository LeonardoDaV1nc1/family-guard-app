import 'package:flutter/material.dart';

class ListaAlertasResponsavelScreen extends StatelessWidget {
  const ListaAlertasResponsavelScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F3),
      appBar: AppBar(
        title: const Text('Alertas e Notificações', style: TextStyle(color: Color(0xFF20352F), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFF20352F)),
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(20.0),
          children: [
            const Text(
              'REGISTO RECENTE',
              style: TextStyle(
                fontSize: 11,
                fontWeight: FontWeight.w700,
                color: Color(0xFF6A7B75),
                letterSpacing: 0.8,
              ),
            ),
            const SizedBox(height: 12),
            
            _buildAlertCard(
              icon: Icons.warning_rounded,
              iconColor: const Color(0xFFB5443D),
              bgColor: const Color(0xFFFEE2E2),
              titulo: 'Sinal de SOS Ativado',
              descricao: 'A Inês enviou um pedido de ajuda urgente.',
              hora: 'Hoje, 16:39',
              naoLido: true,
            ),
            const SizedBox(height: 12),

            _buildAlertCard(
              icon: Icons.shield_outlined,
              iconColor: const Color(0xFF246B5D),
              bgColor: const Color(0xFFE7F2ED),
              titulo: 'Conteúdo Bloqueado',
              descricao: 'Tentativa de acesso a site restrito bloqueada.',
              hora: 'Ontem, 14:15',
              naoLido: false,
            ),
            const SizedBox(height: 12),

            _buildAlertCard(
              icon: Icons.apps,
              iconColor: const Color(0xFF20352F),
              bgColor: const Color(0xFFE2E9E5),
              titulo: 'Nova Aplicação Instalada',
              descricao: 'A aplicação "Discord" foi instalada no dispositivo.',
              hora: '18 Out, 10:20',
              naoLido: false,
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildAlertCard({
    required IconData icon,
    required Color iconColor,
    required Color bgColor,
    required String titulo,
    required String descricao,
    required String hora,
    required bool naoLido,
  }) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(
          color: naoLido ? const Color(0xFFB5443D).withOpacity(0.4) : const Color(0xFFE2E9E5),
          width: naoLido ? 1.5 : 1,
        ),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
              color: bgColor,
              shape: BoxShape.circle,
            ),
            child: Icon(icon, color: iconColor, size: 20),
          ),
          const SizedBox(width: 14),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      titulo,
                      style: TextStyle(
                        fontSize: 14,
                        fontWeight: FontWeight.w700,
                        color: naoLido ? const Color(0xFFB5443D) : const Color(0xFF20352F),
                      ),
                    ),
                    Text(
                      hora,
                      style: const TextStyle(fontSize: 11, color: Color(0xFF6A7B75)),
                    ),
                  ],
                ),
                const SizedBox(height: 4),
                Text(
                  descricao,
                  style: const TextStyle(fontSize: 13, color: Color(0xFF6A7B75)),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}