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
		    String postfix = infixToPostfix(input);
		    int result = evaluatePostfix(postfix); 
		    
		    request.setAttribute("result", result);
		    request.getRequestDispatcher("calculator.jsp").forward(request, response);
	}
	 private int evaluatePostfix(String postfix) {
		    Stack<Integer> stack = new Stack<Integer>();
		    for (int i = 0; i < postfix.length(); i++) {
		      char c = postfix.charAt(i);
		      if (Character.isDigit(c)) {
		        stack.push(c - '0');
		      } else {
		        int b = stack.pop();
		        int a = stack.pop();
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

		  private String infixToPostfix(String infix) {
		    StringBuilder postfix = new StringBuilder();
		    Stack<Character> stack = new Stack<Character>();
		    for (int i = 0; i < infix.length(); i++) {
		      char c = infix.charAt(i);
		      if (Character.isDigit(c)) {
		        postfix.append(c);
		      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
		        while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
		          postfix.append(stack.pop());
		        }
		        stack.push(c);
		      } else if (c == '(') {
		        stack.push(c);
		      } else if (c == ')') {
		        while (!stack.isEmpty() && stack.peek() != '(') {
		          postfix.append(stack.pop());
		        }
		        stack.pop();
		      }
		    }
		    while (!stack.isEmpty()) {
		      postfix.append(stack.pop());
		    }
		    return postfix.toString();
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