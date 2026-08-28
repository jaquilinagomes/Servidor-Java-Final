package utils;
import com.google.gson.Gson;
import model.Pessoa;

public class ExportadorDados {
        public void exportarCarrinhosParaJson(Pessoa cliente) {

            Gson tradutor = new Gson();


            String json = tradutor.toJson(cliente.servicosComprados);


            System.out.println(json);
        }
    }