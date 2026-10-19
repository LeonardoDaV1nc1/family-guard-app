import 'package:flutter/material.dart';

class EmergenciaResponsavelScreen extends StatelessWidget {
  const EmergenciaResponsavelScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFFEE2E2),
      appBar: AppBar(
        title: const Text('Alerta de Emergência', style: TextStyle(color: Color(0xFFB5443D), fontWeight: FontWeight.bold)),
        backgroundColor: Colors.transparent,
        elevation: 0,
        iconTheme: const IconThemeData(color: Color(0xFFB5443D)),
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                children: [
                  Container(
                    width: 90,
                    height: 90,
                    decoration: const BoxDecoration(
                      color: Color(0xFFB5443D),
                      shape: BoxShape.circle,
                    ),
                    child: const Icon(Icons.warning_amber_rounded, color: Colors.white, size: 50),
                  ),
                  const SizedBox(height: 20),
                  const Text(
                    'SOS Ativado por Inês!',
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Color(0xFFB5443D)),
                  ),
                  const SizedBox(height: 8),
                  const Text(
                    'O botão de emergência foi acionado no dispositivo associado.',
                    textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 14, color: Color(0xFF7F1D1D)),
                  ),
                  const SizedBox(height: 30),
                  Container(
                    padding: const EdgeInsets.all(16),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Row(
                      children: const [
                        Icon(Icons.location_on, color: Color(0xFFB5443D)),
                        SizedBox(width: 12),
                        Expanded(
                          child: Text(
                            'Último sinal recebido às 16:39\nLocalização aproximada atualizada.',
                            style: TextStyle(fontSize: 13, color: Color(0xFF20352F)),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  onPressed: () => Navigator.pop(context),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0xFFB5443D),
                    foregroundColor: Colors.white,
                    padding: const EdgeInsets.symmetric(vertical: 16),
                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                  ),
                  child: const Text('Reconhecer e Fechar Alerta', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}