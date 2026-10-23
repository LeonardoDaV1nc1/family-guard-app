
import 'package:dio/dio.dart';

class ApiService {
  ApiService({String? baseUrl})
      : _dio = Dio(
          BaseOptions(
            baseUrl: baseUrl ??
                const String.fromEnvironment(
                  'API_BASE_URL',
                 defaultValue: 'http://10.0.0.159:8080/api/',
                ),
            connectTimeout: const Duration(seconds: 10),
            receiveTimeout: const Duration(seconds: 10),
            sendTimeout: const Duration(seconds: 10),
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'text/plain, application/json',
            },
          ),
        );

  final Dio _dio;

  Future<String> login({
    required String email,
    required String password,
  }) async {
    try {
      final response = await _dio.post(
        'users/login',
        data: {
          'email': email.trim(),
          'password': password,
        },
        options: Options(responseType: ResponseType.plain),
      );

      final data = response.data;
      final token = data is String
          ? data.trim()
          : data is Map
              ? data['token']?.toString().trim()
              : null;

      if (token == null || token.isEmpty) {
        throw Exception('O servidor não devolveu um token válido.');
      }

      return token;
    } on DioException catch (e) {
      throw Exception(errorMessage(e));
    }
  }

  Future<void> register({
    required String name,
    required String email,
    required String password,
    required String role,
  }) async {
    try {
      await _dio.post(
        'users',
        data: {
          'name': name.trim(),
          'email': email.trim(),
          'password': password,
          'role': role,
        },
      );
    } on DioException catch (e) {
      throw Exception(errorMessage(e));
    }
  }

  String errorMessage(Object error) {
    if (error is DioException) {
      final status = error.response?.statusCode;
      final body = error.response?.data;

      String? message;
      if (body is String && body.trim().isNotEmpty) {
        message = body.trim();
      } else if (body is Map) {
        final value = body['message'] ?? body['error'];
        if (value is String && value.trim().isNotEmpty) {
          message = value.trim();
        }
      }

      if (error.response == null) {
        return 'Não foi possível contactar o servidor. '
            'Confirma se o backend está ligado e se o endereço da API está correto.';
      }

      if (status == 401) {
        return message ?? 'E-mail ou palavra-passe incorretos.';
      }

      if (status == 403) {
        return message ?? 'O servidor recusou o pedido.';
      }

      if (status == 400 || status == 409) {
        return message ??
            'Não foi possível concluir. Verifica os dados e se o e-mail já está registado.';
      }

      if (status == 404) {
        return 'Endpoint não encontrado. Verifica o endereço da API.';
      }

      if (status != null && status >= 500) {
        return 'Erro interno do servidor. Tenta novamente mais tarde.';
      }

      return message ?? 'Não foi possível concluir o pedido.';
    }

    return error.toString().replaceFirst('Exception: ', '');
  }
}
