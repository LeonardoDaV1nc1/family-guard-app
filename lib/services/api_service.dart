import 'package:dio/dio.dart';

class ApiService {
  // Se estiveres a usar o emulador Android, o localhost do pc é o 10.0.2.2
  final Dio _dio = Dio(BaseOptions(baseUrl: 'http://10.0.2.2:8080/api'));

  Future<String?> login(String email, String password) async {
    try {
      final response = await _dio.post('/auth/login', data: {
        'email': email,
        'password': password,
      });
      
      // Retorna o token JWT enviado pelo teu backend
      return response.data['token'];
    } catch (e) {
      print('Erro no login: $e');
      return null;
    }
  }
}