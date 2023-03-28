import java.util.*;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CalculatorServlet
 */
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String input = request.getParameter("input");
		    if (input == null || input.isEmpty()) {
		       return;
		    }
		    String prefix = infixToPrefix(input);
		    int result = evaluatePrefix(prefix); 
		    
		    request.setAttribute("result", result);
		    request.getRequestDispatcher("calculator.jsp").forward(request, response);
	}
	private int evaluatePrefix(String prefix) {
	    Stack<Integer> stack = new Stack<Integer>();
	    for (int i = prefix.length() - 1; i >= 0; i--) {
	      char c = prefix.charAt(i);
	      if (Character.isDigit(c)) {
	        stack.push(c - '0');
	      } else {
	        int a = stack.pop();
	        int b = stack.pop();
	        int result = 0;
	        switch (c) {
	          case '+':
	            result = a + b;
	            break;
	          case '-':
	            result = a - b;
	            break;
	          case '*':
	            result = a * b;
	            break;
	          case '/':
	            result = a / b;
	            break;
	        }
	        stack.push(result);
	      }
	    }
	    return stack.pop();
	}

	private String infixToPrefix(String infix) {
	    StringBuilder prefix = new StringBuilder();
	    Stack<Character> stack = new Stack<Character>();
	    for (int i = infix.length() - 1; i >= 0; i--) {
	      char c = infix.charAt(i);
	      if (Character.isDigit(c)) {
	        prefix.append(c);
	      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
	        while (!stack.isEmpty() && precedence(stack.peek()) > precedence(c)) {
	          prefix.append(stack.pop());
	        }
	        stack.push(c);
	      } else if (c == ')') {
	        stack.push(c);
	      } else if (c == '(') {
	        while (!stack.isEmpty() && stack.peek() != ')') {
	          prefix.append(stack.pop());
	        }
	        stack.pop();
	      }
	    }
	    while (!stack.isEmpty()) {
	      prefix.append(stack.pop());
	    }
	    return prefix.reverse().toString();
	}

		  private int precedence(char operator) {
		    switch (operator) {
		      case '+':
		      case '-':
		        return 1;
		      case '*':
		      case '/':
		        return 2;
		      default:
		        return 0;
		    }
		  }
		}