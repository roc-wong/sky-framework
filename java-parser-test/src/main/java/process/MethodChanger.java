package process;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;

public class MethodChanger {

    public static void main(String[] args) throws Exception {
        // parse a file
        JavaParser.parsePackageDeclaration("hbec");

        CompilationUnit cu = JavaParser.parse(new File("F:\\workspace-idea\\basic-trade\\hbec-app-transaction-stock-project\\hbec-app-transaction-stock\\src\\main\\java\\hbec\\app\\ConfigUtils.java"));

        // visit and change the methods names and parameters
        cu.accept(new MethodChangerVisitor(), null);

        // prints the changed compilation unit
        System.out.println(cu);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodChangerVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration methodDeclaration, Void arg) {
            // change the name of the method to upper case
            methodDeclaration.setName(methodDeclaration.getNameAsString().toUpperCase());

            // add a new parameter to the method
            methodDeclaration.addParameter("int", "value");
        }
    }
}