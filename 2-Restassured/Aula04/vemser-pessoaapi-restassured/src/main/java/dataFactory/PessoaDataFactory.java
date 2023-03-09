package dataFactory;

import model.PessoaModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

public class PessoaDataFactory {
    private static Faker faker = new Faker();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static PessoaModel pessoaValida() {
        return novaPessoa();
    }

    public static PessoaModel pessoaComNomeEmBranco() {
        PessoaModel pessoaComNomeEmBranco = pessoaValida();
        pessoaComNomeEmBranco.setNome(StringUtils.EMPTY);

        return pessoaComNomeEmBranco;
    }

    private static PessoaModel novaPessoa() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(faker.name().nameWithMiddle());
        pessoa.setDataNascimento(dateFormat.format(faker.date().birthday()));
        pessoa.setCpf(faker.cpf().valid(false));
        pessoa.setEmail(faker.internet().emailAddress());

        return pessoa;
    }


}
