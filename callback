public interface CallbackService {
    void enviarCallback(Empresa empresa, double valor, String tipoTransacao);
}

@Service
public class WebhookCallbackService implements CallbackService {

    @Override
    public void enviarCallback(Empresa empresa, double valor, String tipoTransacao) {
        // Implementação para enviar callback via HTTP
    }
}

public interface NotificacaoService {
    void enviarNotificacao(Cliente cliente, String mensagem);
}

@Service
public class EmailNotificacaoService implements NotificacaoService {

    @Override
    public void enviarNotificacao(Cliente cliente, String mensagem) {
        // Implementação para enviar e-mail
    }
}




