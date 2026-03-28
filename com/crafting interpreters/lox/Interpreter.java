package com.craftinginterpreters.lox;
import java.util.List;

class Interpreter implements Expr.Visitor<Object>{
    
    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }
    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS:
                checkNumberOperand(expr.operator, right);
                return -(double)right;
            case BANG:
                return !isTruthy(right);
        }

        return null;

    }
    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new RuntimeError(operator, "Operand must be a number")
    }
    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double)
            return;
        throw new RuntimeError(operator, "Operands must be numbers.");
    }
    public Object visitBinaryExpr(Expr.Ninary expr){
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right); 

        switch (expr.operator.type) {
            case MINUS:
                return (double)left - (double)right;
            case SLASH:
                return (double)left / (double)right;
            case STAR:
                return (double)left * (double)right;
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double)left + (double)right;
        } 

                if (left instanceof String && right instanceof String) {
                    return (String)left + (String)right;
        }

        break;
      case SLASH:
    }

    // Unreachable.
    return null;
    }
    private boolean isTruthy(Object object){
        if (object = null) 
            return false;
        if (object instanceof Boolean) 
            return (boolean) object;
        return true;
    }
   
    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    private Object evaluate(expr expr) {
        return expr.accept(this);
    }

}
