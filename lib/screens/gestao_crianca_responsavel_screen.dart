import 'package:flutter/material.dart';

class GestaoCriancaResponsavelScreen extends StatelessWidget {
  final String nomeCrianca;
  final String idadeCrianca;
  final String dispositivo;

  const GestaoCriancaResponsavelScreen({
    super.key,
    this.nomeCrianca = 'Inês Martins',
    this.idadeCrianca = '12 anos',
    this.dispositivo = 'Galaxy A34 · Android 14',
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F3),
      appBar: AppBar(
        title: const Text('Gestão da Criança', style: TextStyle(color: Color(0xFF20352F), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFF20352F)),
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(20.0),
          children: [

            Container(
              padding: const EdgeInsets.all(20),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(16),
                border: Border.all(color: const Color(0xFFE2E9E5)),
              ),
              child: Row(
                children: [
                  Container(
                    width: 56,
                    height: 56,
                    decoration: BoxDecoration(
                      color: const Color(0xFFE9D9C7),
                      borderRadius: BorderRadius.circular(100),
                    ),
                    child: Center(
                      child: Text(
                        nomeCrianca.substring(0, 2).toUpperCase(),
                        style: const TextStyle(
                          color: Color(0xFF775F48),
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          nomeCrianca,
                          style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Color(0xFF20352F)),
                        ),
                        const SizedBox(height: 2),
                        Text('$idadeCrianca · $dispositivo', style: const TextStyle(fontSize: 12, color: Color(0xFF6A7B75))),
                      ],
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 24),

            const Text(
              'CONFIGURAÇÕES DE SEGURANÇA',
              style: TextStyle(
                fontSize: 11,
                fontWeight: FontWeight.w700,
                color: Color(0xFF6A7B75),
                letterSpacing: 0.8,
              ),
            ),
            const SizedBox(height: 12),

            Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(16),
                border: Border.all(color: const Color(0xFFE2E9E5)),
              ),
              child: Column(
                children: [
                  _buildSwitchTile(
                    icon: Icons.shield_outlined,
                    titulo: 'Bloqueio de Conteúdo Sensível',
                    subtitulo: 'Filtrar automaticamente sites e conteúdos inadequados',
                    valorInicial: true,
                    onChanged: (val) {},
                  ),
                  const Divider(height: 1, color: Color(0xFFE2E9E5)),
                  _buildSwitchTile(
                    icon: Icons.notifications_active_outlined,
                    titulo: 'Notificações de Emergência',
                    subtitulo: 'Receber avisos imediatos de SOS',
                    valorInicial: true,
                    onChanged: (val) {},
                  ),
                  const Divider(height: 1, color: Color(0xFFE2E9E5)),
                  _buildSwitchTile(
                    icon: Icons.apps_outlined,
                    titulo: 'Inventário de Aplicações',
                    subtitulo: 'Monitorizar novas instalações de apps',
                    valorInicial: true,
                    onChanged: (val) {},
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildSwitchTile({
    required IconData icon,
    required String titulo,
    required String subtitulo,
    required bool valorInicial,
    required ValueChanged<bool> onChanged,
  }) {
    return SwitchListTile(
      secondary: Icon(icon, color: const Color(0xFF246B5D)),
      title: Text(titulo, style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w700, color: Color(0xFF20352F))),
      subtitle: Text(subtitulo, style: const TextStyle(fontSize: 12, color: Color(0xFF6A7B75))),
      value: valorInicial,
      activeColor: const Color(0xFF246B5D),
      onChanged: onChanged,
    );
  }
}