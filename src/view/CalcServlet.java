package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Calculadora;

@WebServlet("/calculadora")
public class CalcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private double resp = 0;
	private String expressao = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Calculadora Servlet - Recebe parâmetros via QueryString
		// Exemplo: http://localhost:8080/prjCalculadoraServlet/calculadora?valor1=12&valor2=4&operacao=somar
		
        PrintWriter out = response.getWriter();	
        response.setContentType("text/html");
		
        double valor1 = Double.parseDouble(request.getParameter("valor1"));
        double valor2 = Double.parseDouble(request.getParameter("valor2"));
        String operacao = request.getParameter("operacao");
        
        Calculadora c = new Calculadora();
        String sinalOperacao = "Inválido";
        
        switch(operacao){
		case("somar"):
			resp = c.somar(valor1, valor2);
			sinalOperacao = "+";
			break;	
			
		case("subtrair"):
			resp = c.subtrair(valor1, valor2);
			sinalOperacao = "-";
			break;	
			
		case("multiplicar"):
			resp = c.multiplicar(valor1, valor2);
			sinalOperacao = "x";
			break;	
			
		case("dividir"):
			try{
				resp = c.dividir(valor1, valor2);
				sinalOperacao = "/";
			}
			catch(ArithmeticException a){
				resp = 0;
			}
			break;	
			
		default:
			break;
        }
        
        expressao = valor1 + " " + sinalOperacao  + " " + valor2;
        		
        out.println("<html>");
        out.println("<body>");
        out.println("<title>Calculadora</title>");
        out.println("<h1>" + "O resultado da operação é: " + resp + "</h1>");
        out.println("<h2>" + "Expressão: " + expressao + "</h2>");
        out.println("</body>");
        out.println("</html>");
		
	}

}
