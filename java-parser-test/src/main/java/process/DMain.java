package process;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author roc
 * @date 2018/01/03
 */
public class DMain {

    public static void main(String[] args) throws IOException {

        File file = new File("F:\\workspace-idea\\basic-trade\\hbec-app-transaction-stock-project\\hbec-app-transaction-stock\\src\\test\\java\\hbec\\app\\transaction\\stock\\process\\D.java");

        CompilationUnit cu = JavaParser.parse(file);

        // visit and change the methods names and parameters
        cu.accept(new MyVisitor(), null);

        // prints the changed compilation unit
        System.out.println(cu);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(cu.toString());
        fileWriter.flush();

    }

    static class MyVisitor extends ModifierVisitor<Void> {
        @Override
        public Node visit(VariableDeclarator declarator, Void args) {
            if (declarator.getNameAsString().equals("a")
                    // the initializer is optional, first check if there is one
                    && declarator.getInitializer().isPresent()) {
                Expression expression = declarator.getInitializer().get();
                // We're looking for a literal integer.
                if (expression instanceof IntegerLiteralExpr) {
                    // We found one. Is it literal integer 20?
                    if (((IntegerLiteralExpr) expression).getValue().equals("20")) {
                        // Returning null means "remove this VariableDeclarator"
                        return null;
                    }
                }
            }
            return declarator;
        }
    }
}
