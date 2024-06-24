
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Funcionario.Funcionario;


public class App {
    
    public static void main(String[] args) throws Exception {
      Scanner dados = new Scanner(System.in);

       List<Funcionario> funcionarios = new ArrayList<>();
       Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();


       funcionarios.add(new Funcionario("joão victor", LocalDate.of(2000, 02, 23), new BigDecimal("12000.00"), "dev java"));
       funcionarios.add(new Funcionario("ana bianca", LocalDate.of(2004, 3, 30), new BigDecimal("2000.00"), "analista"));
       funcionarios.add(new Funcionario("wyrisvander", LocalDate.of(2004, 3, 30), new BigDecimal("5000.00"), "engenheiro"));
       funcionarios.add(new Funcionario("vitoria", LocalDate.of(2004, 12, 30), new BigDecimal("5000.00"), "analista"));
       funcionarios.add(new Funcionario("pedro", LocalDate.of(2004, 3, 30), new BigDecimal("3000.00"), "analista"));
       funcionarios.add(new Funcionario("clara", LocalDate.of(2004, 3, 30), new BigDecimal("4000.00"), "dev java"));
      
     
      
       
       System.out.println("Digite o número correspondente para cada operação:");
       System.out.println("1 - Adicionar funcionários");
       System.out.println("2 - Mostrar todos os funcionários");
       System.out.println("3 - Remover o funcionário 'João'");
       System.out.println("4 - Dar aumento de 10%");
       System.out.println("5 - Agrupar funcionários por função e mostrar");
       System.out.println("6 - Mostrar aniversariantes dos meses 10 e 12");
       System.out.println("7 - Mostrar funcionário mais velho");
       System.out.println("8 - Mostrar lista de funcionários em ordem alfabética");
       System.out.println("9 - Mostrar total dos salários");
       System.out.println("10 - Mostrar quantos salários mínimos cada funcionário ganha");
      int pararOperacao = dados.nextInt();
      dados.nextLine();

         switch (pararOperacao) {
          case 1:
             int quantidade;
             System.out.println("quantos funcinarios ira adicionar?");
             quantidade = dados.nextInt();
             dados.nextLine();
             
             for (int i = 0; i< quantidade; i++) {
               
              System.out.println("Funcionário " + (i + 1) + ":");
              System.out.print("Digite o nome: ");
              String nome = dados.nextLine();

              System.out.print("Digite o cargo: ");
              String funcao = dados.nextLine();

              System.out.print("Digite o salário (utilize ',' como separador decimal): ");
              String salarioStr = dados.nextLine();
              
              salarioStr = salarioStr.replace(',', '.');
              BigDecimal salario = new BigDecimal(salarioStr.trim()); 
  

              System.out.print("Digite a data de nascimento (formato dd/MM/yyyy): ");
              String dataNascimentoStr = dados.nextLine();

             
              LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

              Funcionario funcionario = new Funcionario(nome,dataNascimento,salario,funcao);
              funcionarios.add(funcionario);
              
              listaFuncionarios(funcionarios);
              

             }
            break;

            case 2:

            listaFuncionarios(funcionarios);

            break;

            case 3:
            String NomeDoFuncionario ;
            System.out.println("digite o nome do funcionario");
            NomeDoFuncionario = dados.nextLine();
            RemoverFuncionarioPorNome(funcionarios,NomeDoFuncionario);
            System.out.println("funcionario " + NomeDoFuncionario + " removido com sucesso");
            System.out.println("-------------------");
            listaFuncionarios(funcionarios);
            break;

           case 4:
           double aumento;
           
           System.out.println("quantos por cento de aumento? ");
           aumento = dados.nextDouble();
           dados.nextLine();

           
           BigDecimal aumentoDecimal = BigDecimal.valueOf(aumento).divide(BigDecimal.valueOf(100));
           
           for (Funcionario func : funcionarios) {
            BigDecimal aumentoValor = func.getSalario().multiply(aumentoDecimal);
            @SuppressWarnings("deprecation")
            BigDecimal novoSalario = func.getSalario().add(aumentoValor).setScale(1,BigDecimal.ROUND_CEILING);
            func.setSalario(novoSalario);
          }
          System.out.println("Salarios atualizados");
          listaFuncionarios(funcionarios);

           break;

           case 5:

           for (Funcionario func : funcionarios) {
            String funcao = func.getFuncao();
            
            // Usa computeIfAbsent para garantir que a lista seja inicializada se não existir
            funcionariosPorFuncao.computeIfAbsent(funcao, key -> new ArrayList<>()).add(func);
        }

        // Imprime os funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario func : entry.getValue()) {
                System.out.println(func);
            }
            System.out.println(); // Linha em branco para separar as funções
        }

           break;

           case 6:

            for(Funcionario func : funcionarios){
              int MesAniversario = func.getDataNascimento().getMonthValue();
              if(MesAniversario == 10 || MesAniversario == 12 ){
                System.out.println(func);
              }
            }

            break;

            case 7:
            
            Funcionario funcionarioMaisVelho = funcionarios.get(0);
            for(Funcionario func : funcionarios){
             if(func.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())){
                 funcionarioMaisVelho = func;

             }
               
            }

           LocalDate dataAtual = LocalDate.now();
            int idadeFuncionario = Period.between(funcionarioMaisVelho.getDataNascimento(), dataAtual).getYears();
            System.out.println(idadeFuncionario);
            break;

            case 8:
              Comparator<Funcionario> comparatorPorNome = Comparator.comparing(Funcionario::getNome);
              funcionarios.sort(comparatorPorNome);
        
            for (Funcionario func : funcionarios) {
              System.out.println(func);
             
             }
   

            break;

            case 9:
            double somaSalarios = funcionarios.stream()
            .mapToDouble(funcionario -> funcionario.getSalario().doubleValue())
                                          .sum();
             System.out.printf("Soma dos salários: %.2f%n", somaSalarios);
            break;
    
            case 10:
            BigDecimal salarioMinimo = new BigDecimal("1212.00"); 
            
            for (Funcionario func : funcionarios) {
    BigDecimal salarioFuncionario = func.getSalario();
    BigDecimal quantosSalariosMinimos = salarioFuncionario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    System.out.printf("Funcionário %s ganha %.2f salários mínimos.%n", func.getNome(), quantosSalariosMinimos);


}






            break;
         
          default:
               System.out.println("valor não valido");
            break;
         }
 
      
    }
      

    public static   void RemoverFuncionarioPorNome(List<Funcionario> funcionarios,String nome){
      funcionarios.removeIf(f ->  f.getNome().equals(nome));
        
    }

    public static void listaFuncionarios(List<Funcionario> funcionarios){
      for (Funcionario func : funcionarios) {
        System.out.println(func);
      }
    }

 



















  
}
