package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PropondoLanceSteps {

    private Leilao leilao;

    private List<Lance> lista;

    private void addNewLance(Double valor, String nomeUsuario) {
        Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
        lista.add(lance);
    }

    @Before
    public void setup() {
        this.lista = new ArrayList<>();
        leilao = new Leilao("Tablet XPTO");
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
        addNewLance(valor, nomeUsuario);
    }

    @Dado("um lance invalido de {double} reais do usuario {string}")
    public void um_lance_invalido_de_reais_do_usuario(Double valor, String nomeUsuario) {
        try {
            addNewLance(valor, nomeUsuario);
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Dado("dois lances")
    public void dois_lances(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(test -> {
            String nomeUsuario = test.get("nomeUsuario");
            String valor = test.get("valor");

            addNewLance(Double.parseDouble(valor), nomeUsuario);
        });
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        this.lista.forEach(lance -> leilao.propoe(lance));
    }

    @Entao("Apenas os lances validos sao aceitos")
    public void apenas_os_lances_validos_sao_aceitos() {
        Assert.assertEquals(this.lista.size(), leilao.getLances().size());

        this.lista.forEach(lance -> {
            Assert.assertEquals(lance.getValor(), leilao.getLances().get(leilao.getLances().indexOf(lance)).getValor());
        });
    }
}