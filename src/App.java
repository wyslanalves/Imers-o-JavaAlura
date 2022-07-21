import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão http e buscar os top filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        //String url = "https://api.nasa.gov/planetary/apod?api_key=S406GUZ1wwx2jRDN44rf5O5Na9nqlF2gdDPgJdJ7";
        
        var http =  new ClienteHttp();
        String json = http.buscaDados(url);

       

        // exibir e manipular os dados
        //var extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);


        var geradora = new GeradaraDeFigurinhas();
        for (int i = 0; i < 10; i++) {

            Conteudo conteudo = conteudos.get(i);

           
           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" +  conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            //System.out.println(filme.get("title") + "\n" + filme.get("image") + "\n" + filme.get("imDbRating"));
            //System.out.println("==========================================================================================================================================");
        }

    }
}
