import 'package:flutter/material.dart';
import 'package:family_guard_app/screens/login_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Elo - Segurança Familiar',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF246B5D), 
        ),
        scaffoldBackgroundColor: const Color(0xFFF5F7F3),
        useMaterial3: true,
      ),
      home: const LoginScreen(),
    );
  }
}