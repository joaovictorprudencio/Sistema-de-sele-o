package Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Pessoa.Pessoa;


public class Funcionario extends Pessoa {
    
private BigDecimal salario;
private String funcao;



    public Funcionario(String nome ,LocalDate dataNascimento , BigDecimal salario,String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }


     @Override
    public String toString() {
      
        return "Funcionário{" +
                "nome | '" + nome + '\'' +
                "| dataNascimento | " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "| salario | " + salario +
                " funcao | '" + funcao + '\'' +
                '}';
    }


    public String getFuncao() {
        return this.funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }




}