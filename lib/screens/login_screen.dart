import 'dart:convert';
import 'visao_geral_responsavel_screen.dart';
import 'associar_responsavel_screen.dart';
import 'registo_screen.dart';
import 'package:flutter/material.dart';
import '../services/api_service.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _emailController = TextEditingController();
  final _senhaController = TextEditingController();
  final _apiService = ApiService();

  bool _isLoading = false;
  String _perfilEscolhido = 'PARENT';

  @override
  void dispose() {
    _emailController.dispose();
    _senhaController.dispose();
    super.dispose();
  }

  Future<void> _fazerLogin() async {
    final email = _emailController.text.trim();
    final senha = _senhaController.text;

    if (email.isEmpty || senha.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Preenche o e-mail e a senha.'),
        ),
      );
      return;
    }

    setState(() => _isLoading = true);

    try {
      final token = await _apiService.login(
        email: email,
        password: senha,
      );

      final partes = token.split('.');
      if (partes.length != 3) {
        throw Exception('O servidor devolveu um token inválido.');
      }

      final payload = base64Url.normalize(partes[1]);
      final dados = jsonDecode(
        utf8.decode(base64Url.decode(payload)),
      ) as Map<String, dynamic>;

      final perfil = dados['role'];

      if (perfil != _perfilEscolhido) {
        throw Exception(
          _perfilEscolhido == 'PARENT'
              ? 'Esta conta não é de responsável. Escolhe Criança.'
              : 'Esta conta não é de criança. Escolhe Responsável.',
        );
      }

      if (!mounted) return;

      if (perfil == 'PARENT') {
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(
            builder: (_) => const VisaoGeralResponsavelScreen(),
          ),
        );
      } else if (perfil == 'CHILD') {
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(
            builder: (_) => const AssociarResponsavelScreen(),
          ),
        );
      } else {
        throw Exception('Perfil de usuário desconhecido.');
      }
    } catch (e) {
      if (!mounted) return;

      final mensagem = e.toString().replaceFirst('Exception: ', '');
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(mensagem),
          backgroundColor: Colors.red.shade700,
        ),
      );
    } finally {
      if (mounted) {
        setState(() => _isLoading = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F7F3),
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.symmetric(horizontal: 24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  width: 64,
                  height: 64,
                  decoration: BoxDecoration(
                    color: const Color(0xFF246B5D),
                    borderRadius: BorderRadius.circular(16),
                  ),
                  child: const Icon(
                    Icons.link,
                    color: Colors.white,
                    size: 36,
                  ),
                ),
                const SizedBox(height: 16),
                const Text(
                  'elo',
                  style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.w800,
                    color: Color(0xFF20352F),
                    letterSpacing: -0.5,
                  ),
                ),
                const SizedBox(height: 6),
                const Text(
                  'Segurança Familiar',
                  style: TextStyle(
                    fontSize: 14,
                    color: Color(0xFF6A7B75),
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(height: 40),
                Container(
                  padding: const EdgeInsets.all(24),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(16),
                    border: Border.all(
                      color: const Color(0xFFE2E9E5),
                    ),
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      const Text(
                        'Entrar na conta',
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.w700,
                          color: Color(0xFF20352F),
                        ),
                      ),
                      const SizedBox(height: 20),
                      const Text(
                        'Entrar como',
                        style: TextStyle(
                          fontWeight: FontWeight.w600,
                          color: Color(0xFF20352F),
                        ),
                      ),
                      const SizedBox(height: 10),
                      Row(
                        children: [
                          Expanded(
                            child: ChoiceChip(
                              avatar: const Icon(Icons.family_restroom),
                              label: const Text('Responsável'),
                              selected: _perfilEscolhido == 'PARENT',
                              onSelected: _isLoading
                                  ? null
                                  : (_) => setState(
                                      () => _perfilEscolhido = 'PARENT',
                                    ),
                            ),
                          ),
                          const SizedBox(width: 8),
                          Expanded(
                            child: ChoiceChip(
                              avatar: const Icon(Icons.child_care),
                              label: const Text('Criança'),
                              selected: _perfilEscolhido == 'CHILD',
                              onSelected: _isLoading
                                  ? null
                                  : (_) => setState(
                                      () => _perfilEscolhido = 'CHILD',
                                    ),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 20),
                      TextField(
                        controller: _emailController,
                        keyboardType: TextInputType.emailAddress,
                        autocorrect: false,
                        enabled: !_isLoading,
                        decoration: InputDecoration(
                          labelText: 'E-mail',
                          hintText: 'exemplo@email.com',
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                          prefixIcon: const Icon(Icons.email_outlined),
                        ),
                      ),
                      const SizedBox(height: 16),
                      TextField(
                        controller: _senhaController,
                        obscureText: true,
                        enabled: !_isLoading,
                        onSubmitted: (_) => _fazerLogin(),
                        decoration: InputDecoration(
                          labelText: 'Senha',
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                          prefixIcon: const Icon(Icons.lock_outline),
                        ),
                      ),
                      const SizedBox(height: 24),
                      ElevatedButton(
                        onPressed: _isLoading ? null : _fazerLogin,
                        style: ElevatedButton.styleFrom(
                          backgroundColor: const Color(0xFF246B5D),
                          foregroundColor: Colors.white,
                          padding: const EdgeInsets.symmetric(vertical: 14),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                          elevation: 0,
                        ),
                        child: _isLoading
                            ? const SizedBox(
                                height: 20,
                                width: 20,
                                child: CircularProgressIndicator(
                                  color: Colors.white,
                                  strokeWidth: 2,
                                ),
                              )
                            : const Text(
                                'Entrar',
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.w700,
                                ),
                              ),
                      ),
                      const SizedBox(height: 12),
                      TextButton(
                        onPressed: _isLoading
                            ? null
                            : () {
                                Navigator.of(context).push(
                                  MaterialPageRoute(
                                    builder: (_) => const RegistoScreen(),
                                  ),
                                );
                              },
                        child: const Text('Ainda não tens conta? Registar'),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
