/**
 * Name: Rusho Binnabi
 * Date: 10/13/2023
 * Assignment: Interpreter 1
 * Class: ICSI 311 - Fall 2023
 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

    // this Interpreter class is the main Interpreter class that will try to manage things for the program.

    private HashMap<String, InterpreterDataType> globalVariableStorage;
    private HashMap<String, FunctionDefinitionNode> functionSource;
    private List<String> file;
    private static InterpreterDataType FS;
    private static InterpreterDataType NF;
    private static InterpreterDataType NR;
    private static InterpreterDataType FNR;
    private static InterpreterDataType FILENAME;
    private static InterpreterDataType OFMT;
    private static InterpreterDataType OFS;
    private static InterpreterDataType ORS;
    private static FunctionDefinitionNode andThen;
    private static FunctionDefinitionNode apply;
    private static FunctionDefinitionNode compose;
    private static FunctionDefinitionNode identity;
    private LineManager lineManager;
    private ProgramNode programNode;

    /**
     * this setProgramNode() method sets the program node.
     * @param programNode the program node.
     */
    public void setProgramNode(ProgramNode programNode) {
        this.programNode = programNode;
    }

    /**
     * this getProgramNode() method gets the program node.
     * @return the program node.
     */
    public ProgramNode getProgramNode() {
        return programNode;
    }

    /**
     * this interpretProgram() method runs the start and end blocks, calls the SplitAndAssign() method,
     * and it also runs the other blocks for each line of input.
     * @throws Exception throws an exception if it can't interpret blocks.
     */

    public void interpretProgram() throws Exception {
        int i = 0;
        BlockNode blockNode = new BlockNode(null, null);
        Interpreter.LineManager manageLine = new LineManager(getFile());
        if (getProgramNode().getStartBlock(i).equals(blockNode)) {
            interpretBlock(getProgramNode().getStartBlock(i));
            while (!manageLine.splitAndAssign()) {
                interpretBlock(getProgramNode().getStartBlock(i));
                i++;
            }
        }
        else if (getProgramNode().getEndBlock(i).equals(blockNode)) {
            interpretBlock(getProgramNode().getEndBlock(i));
            while (!manageLine.splitAndAssign()) {
                interpretBlock(getProgramNode().getEndBlock(i));
                i++;
            }
        }
    }

    /**
     * this interpretBlock() method checks the condition of the BlockNode and tries to process statements.
     * @param blockNode the block node being checked.
     * @throws Exception throws an exception if something goes wrong while trying to process statements.
     */

    public void interpretBlock(BlockNode blockNode) throws Exception {
        InterpreterDataType idt = getIDT(blockNode, getGlobalVariableStorage());
        if (blockNode.getCondition().isPresent()) {
            if (idt != null) {
                for (int i = 0; i < blockNode.getStatements().size(); i++) {
                    processStatement(getGlobalVariableStorage(), blockNode.getStatements().get(i));
                }
            }
        }
        else if (!blockNode.getCondition().isPresent() || idt != null) {
            for (int j = 0; j < blockNode.getStatements().size(); j++) {
                processStatement(getGlobalVariableStorage(), blockNode.getStatements().get(j));
            }
        }
    }

    /**
     * this interpretListOfStatements() method loops over the statements, calls the processStatements() for each one, but it also
     * checks the return type from each call of the processStatements() method and if it isn't None, then it returns passing "up"
     * the same return type.
     * @param statements the linked list of statement nodes.
     * @param localVariables the hashmap of local variables.
     * @return if the return type isn't None, then it returns passing "up" the same return type.
     */

    public ReturnType interpretListOfStatements(LinkedList<StatementNode> statements, HashMap<String, InterpreterDataType> localVariables) throws Exception {
        for (int i = 0; i < statements.size(); i++) {
            if (processStatement(localVariables, statements.get(i)).getReturns() == ReturnType.Returns.NORMAL) {
                return new ReturnType(ReturnType.Returns.NORMAL, String.valueOf(statements.get(i)));
            }
            else if (processStatement(localVariables, statements.get(i)).getReturns() == ReturnType.Returns.BREAK) {
                return new ReturnType(ReturnType.Returns.BREAK, String.valueOf(statements.get(i)));
            }
            else if (processStatement(localVariables, statements.get(i)).getReturns() == ReturnType.Returns.CONTINUE) {
                return new ReturnType(ReturnType.Returns.CONTINUE, String.valueOf(statements.get(i)));
            }
            else if (processStatement(localVariables, statements.get(i)).getReturns() == ReturnType.Returns.RETURN) {
                return new ReturnType(ReturnType.Returns.RETURN, String.valueOf(statements.get(i)));
            }
        }
        return null;
    }

    /**
     * this processStatement() method process the statements according to the actual type of the statements.
     * @param localVariables the hashmap of local variables.
     * @param statement the statement being processed.
     * @return a return type based on the type of statement.
     * @throws Exception throws an exception if there is any other type of node.
     */

    public ReturnType processStatement(HashMap<String, InterpreterDataType> localVariables, StatementNode statement) throws Exception {
        ReturnType type = new ReturnType(null, null);
        if (statement instanceof AssignmentNode) {
            Node leftSide = getIDT(statement, localVariables);
            Node rightSide;
            if (leftSide != null) {
                rightSide = getIDT(((AssignmentNode) statement).getTarget(), localVariables);
                type.setReturnValue(String.valueOf(rightSide));
            }
            type.setReturnValue(String.valueOf(leftSide));
            type.setReturns(ReturnType.Returns.NORMAL);
        }
        else if (statement instanceof BreakNode) {
            type.setReturns(ReturnType.Returns.BREAK);
        }
        else if (statement instanceof ContinueNode) {
            type.setReturns(ReturnType.Returns.CONTINUE);
        }
        else if (statement instanceof DeleteNode) {
            ArrayList<InterpreterDataType> localArray = new ArrayList<>((Collection) localVariables.get(((DeleteNode) statement).getArrayReferenceName()));
            ArrayList<InterpreterDataType> globalArray = new ArrayList<>((Collection) getGlobalVariableStorage().get(((DeleteNode) statement).getArrayReferenceName()));
            for (int i = 0; i < localArray.size(); i++) {
                if (localArray.contains(((DeleteNode) statement).getArrayReferenceName())) {
                    localArray.remove(i);
                }
                else {
                    localArray.remove(i);
                }
            }
            for (int j = 0; j < globalArray.size(); j++) {
                if (localArray.contains(((DeleteNode) statement).getArrayReferenceName())) {
                    localArray.remove(j);
                }
                else {
                    localArray.remove(j);
                }
            }

        }
        else if (statement instanceof DoWhileNode) {
            do {
                if (interpretListOfStatements(((DoWhileNode) statement).getParseDoWhileStatements().getStatements(), localVariables).getReturns() == ReturnType.Returns.BREAK) {
                    break;
                }
                else if (interpretListOfStatements(((DoWhileNode) statement).getParseDoWhileStatements().getStatements(), localVariables).getReturns() == ReturnType.Returns.RETURN) {
                    return processStatement(localVariables, statement);
                }
            } while (getIDT(statement, localVariables) != null);
        }
        else if (statement instanceof ReturnNode) {
            if (statement != null) {
                type.setReturnValue(statement.toString());
            }
            else {
                return new ReturnType(ReturnType.Returns.RETURN, statement.toString());
            }
        }
        else if (statement instanceof WhileNode) {
            while (getIDT(statement, localVariables) != null) {
                if (interpretListOfStatements(((WhileNode) statement).getParseWhileStatements().getStatements(), localVariables).getReturns() == ReturnType.Returns.BREAK) {
                    break;
                }
                else if (interpretListOfStatements(((WhileNode) statement).getParseWhileStatements().getStatements(), localVariables).getReturns() == ReturnType.Returns.RETURN) {
                    return processStatement(localVariables, statement);
                }
            }
        }
        else if (statement instanceof FunctionCallNode) {
            runFunctionCall((FunctionCallNode) ((FunctionCallNode) statement).getFunctionCallNode(), localVariables);
        }
        else if (statement instanceof IfNode) {
            for (int i = 0; i < ((IfNode) statement).getParseOperationCondition().toString().length(); i++) {
                if (((IfNode) statement).getParseOperationCondition() == null || Boolean.parseBoolean(String.valueOf(getIDT(((IfNode) statement).getParseOperationCondition(), localVariables)))) {
                    if (interpretListOfStatements(((IfNode) statement).getParseBlockStatements().getStatements(), localVariables).getReturns() != ReturnType.Returns.NORMAL) {
                        return interpretListOfStatements(((IfNode) statement).getParseBlockStatements().getStatements(), localVariables);
                    }
                }
            }
        }
        else if (statement instanceof ForNode) {
            LinkedList<StatementNode> listOfStatementNodes = new LinkedList<>();
            if (((ForNode) statement).getForNode() != null) {
                processStatement(localVariables, statement);
            }
            // start of processing for the for each type of statement since there isn't really a ForEachNode.java class.
            else if (((ForNode) statement).getForEachNode() != null) {
                if (localVariables != null) {
                    while (((ForNode) statement).getForEachNode() != null) {
                        listOfStatementNodes.add(statement);
                    }
                    for (int i = 0; i < listOfStatementNodes.size(); i++) {
                        type.setReturnValue(localVariables.get(String.valueOf(i)).getValue());
                        type.setReturns(ReturnType.Returns.BREAK);
                        if (interpretListOfStatements(listOfStatementNodes, localVariables).equals(type)) {
                            type.setReturns(ReturnType.Returns.RETURN);
                            break;
                        }
                        else if (interpretListOfStatements(listOfStatementNodes, localVariables).equals(type)) {
                            return processStatement(localVariables, statement);
                        }
                    }
                }
            }
            // end of processing for the for each type of statement since there isn't really a ForEachNode.java class.
            else {
                while (((ForNode) statement).getForNode() != null) {
                    listOfStatementNodes.add(statement);
                    for (int i = 0; i < listOfStatementNodes.size(); i++) {
                        type.setReturns(ReturnType.Returns.BREAK);
                        if (interpretListOfStatements(listOfStatementNodes, localVariables).equals(type)) {
                            type.setReturns(ReturnType.Returns.RETURN);
                            break;
                        }
                        else if (interpretListOfStatements(listOfStatementNodes, localVariables).equals(type)) {
                            return processStatement(localVariables, statement);
                        }
                        processStatement(localVariables, statement); // calls processStatement() on the forNode's increment.
                    }
                }
            }
        }
        else {
            if (getIDT(statement, localVariables) == null) {
                throw new Exception();
            }
        }
        return type;
    }

    /**
     * this getIDT() method evaluates the node and returns an InterpreterDataType.
     * @param node the node.
     * @param localVariables the hashmap of local variables.
     * @return an InterpreterDataType.
     */
    public InterpreterDataType getIDT(Node node, HashMap<String, InterpreterDataType> localVariables) throws Exception {
        if (node instanceof AssignmentNode) {
            var t = ((AssignmentNode) node).getTarget();
            return new InterpreterDataType(String.valueOf(assignmentNode((InterpreterDataType) t)));
        }
        else if (node instanceof ConstantNode) {
            var t = ((ConstantNode) node).getConstantNode();
            return new InterpreterDataType(String.valueOf(constantNode(t)));
        }
        else if (node instanceof PatternNode) {
            var t = ((PatternNode) node).getPatternNode();
            return new InterpreterDataType(String.valueOf(patternNode(t)));
        }
        else if (node instanceof TernaryNode) {
            var t = ((TernaryNode) node).getCondition();
            if (Integer.parseInt(t.toString()) != 0) {
                t = ((TernaryNode) node).getConsequent(); // tries to get the true case.
            }
            else {
                t = ((TernaryNode) node).getAlternate(); // if the true case fails, then it tries to get the false case.
            }
            return new InterpreterDataType(String.valueOf(ternaryNode((InterpreterDataType) t, localVariables)));
        }
        else if (node instanceof VariableReferenceNode) {
            var t = ((VariableReferenceNode) node).getVariableName();
            if (((VariableReferenceNode) node).getVariableName().equals(t)) {
                InterpreterDataType index = (InterpreterDataType) ((VariableReferenceNode) node).getIndexExpression().get();
                if (getGlobalVariableStorage().containsKey(t)) {
                    return new InterpreterDataType(index.getValue());
                }
                else {
                    throw new Exception();
                }
            }
            else {
                return new InterpreterDataType(String.valueOf(variableReferenceNode(t)));
            }
        }
        else if (node instanceof OperationNode) {
            var t = ((OperationNode) node).getOperation();
            if (t == OperationNode.Operations.ADD) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.SUBTRACT) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.MULTIPLY) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.DIVIDE) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.EXPONENT) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.MODULO) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.EQ) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.NE) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.AND) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.OR) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.NOT) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.MATCH) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.NOTMATCH) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.DOLLAR) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.UNARYPOS) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.UNARYNEG) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.PREINC) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.POSTINC) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.PREDEC) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.POSTDEC) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.CONCATENATION) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
            else if (t == OperationNode.Operations.IN) {
                return new InterpreterDataType(String.valueOf(operationNode(node, localVariables)));
            }
        }
        return null;
    }

    /**
     * this operationNode() method evaluates the left and right (if any) InterpreterDataTypes using the getIDT() method and tries to do the operation.
     * @param node the node.
     * @param variables the hashmap of local variables.
     * @return an InterpreterDataType which is the value of the result of the operation.
     * @throws Exception throws an error if something goes wrong during the operation.
     */

    public InterpreterDataType operationNode(Node node, HashMap<String, InterpreterDataType> variables) throws Exception {
        float num1, num2;
        String result = "";
        InterpreterDataType idt1; // the left side.
        InterpreterDataType idt2 = null; // the right side.
        try {
            // start of math operations.
            if (node.toString().equals(OperationNode.Operations.ADD.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(num1 + num2);
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.SUBTRACT.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(num1 - num2);
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.MULTIPLY.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(num1 * num2);
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.DIVIDE.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(num1 / num2);
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.EXPONENT.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(Math.pow(num1, num2));
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.MODULO.toString())) {
                idt1 = getIDT(node, variables);
                if (idt1 != null) {
                    idt2 = getIDT(node, variables);
                }
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    result = String.valueOf(num1 % num2);
                } catch (Exception e) {
                    System.out.println("Error. The math operation couldn't be interpreted.");
                }
            }
            // end of math operations.
            // start of compare operations.
            else if (node.toString().equals(OperationNode.Operations.EQ.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    if ((((Object) num1).getClass().getSimpleName().equals("Float")) && (((Object) num2).getClass().getSimpleName().equals("Float"))) { // checks to see if the converted values in num1 and num2 are of type Float
                                                                                                                                                        // for the boolean equals operation.
                        if (num1 == num2) {
                            result = "true";
                        } else {
                            result = "false";
                        }
                    } else {
                        if (String.valueOf(num1).equals(String.valueOf(num2))) {
                            result = "true";
                        } else {
                            result = "false";
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error. The compare operation couldn't be interpreted.");
                }
            } else if (node.toString().equals(OperationNode.Operations.NE.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    if ((((Object) num1).getClass().getSimpleName().equals("Float")) && (((Object) num2).getClass().getSimpleName().equals("Float"))) { // checks to see if the converted values in num1 and num2 are of type Float
                                                                                                                                                        // for the boolean not equals operation.
                        if (num1 != num2) {
                            result = "true";
                        } else {
                            result = "false";
                        }
                    } else {
                        if (!String.valueOf(num1).equals(String.valueOf(num2))) {
                            result = "true";
                        } else {
                            result = "false";
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error. The compare operation couldn't be interpreted.");
                }
            }
            // end of compare operations.
            // start of boolean operations.
            else if (node.toString().equals(OperationNode.Operations.AND.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    if ((((Object) num1).getClass().getSimpleName().equals("Float")) && (((Object) num2).getClass().getSimpleName().equals("Float"))) { // checks to see if the converted values in num1 and num2 are of type Float
                                                                                                                                                        // for the boolean and operation.
                        if ((num1 != 0) && (num2 != 0)) {
                            result = "true";
                        }
                        else {
                            result = "false";
                        }
                    }
                    else {
                        result = "false";
                    }
                }
                catch (Exception e) {
                    System.out.println("Error. The boolean operation couldn't be interpreted.");
                }
                return new InterpreterDataType(result);
            }
            else if (node.toString().equals(OperationNode.Operations.OR.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    if ((((Object) num1).getClass().getSimpleName().equals("Float")) && (((Object) num2).getClass().getSimpleName().equals("Float"))) { // checks to see if the converted values in num1 and num2 are of type Float
                                                                                                                                                        // for the boolean or operation.
                        if ((num1 != 0) && (num2 != 0)) {
                            result = "true";
                        }
                        else {
                            result = "false";
                        }
                    }
                    else {
                        result = "false";
                    }
                }
                catch (Exception e) {
                    System.out.println("Error. The boolean operation couldn't be interpreted.");
                }
            }
            else if (node.toString().equals(OperationNode.Operations.NOT.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num2 = Float.parseFloat(String.valueOf(idt2));
                    if ((((Object) num1).getClass().getSimpleName().equals("Float")) && (((Object) num2).getClass().getSimpleName().equals("Float"))) { // checks to see if the converted values in num1 and num2 are of type Float
                                                                                                                                                        // for the boolean not operation.
                        if ((num1 != 0) && (num2 != 0)) {
                            result = "true";
                        }
                        else {
                            result = "false";
                        }
                    }
                    else {
                        result = "false";
                    }
                }
                catch (Exception e) {
                    System.out.println("Error. The boolean operation couldn't be interpreted.");
                }
            }
            // end of boolean operations.
            // start of match operations.
            else if (node.toString().equals(OperationNode.Operations.MATCH.toString())) {
                idt1 = getIDT(node, variables); // left side.
                idt2 = patternNode(String.valueOf(node)); // right side.
                Pattern pattern1 = Pattern.compile(idt1.toString(), Pattern.CASE_INSENSITIVE);
                Matcher match1 = pattern1.matcher(idt1.toString());
                boolean match1Found = match1.find();
                Pattern pattern2 = Pattern.compile(idt2.getValue(), Pattern.CASE_INSENSITIVE);
                Matcher match2 = pattern2.matcher(idt2.getValue());
                boolean match2Found = match2.find();
                if (match1Found && match2Found) {
                    result = "matches";
                }
                else {
                    result = "does not match";
                }
            }
            else if (node.toString().equals(OperationNode.Operations.NOTMATCH.toString())) {
                idt1 = getIDT(node, variables); // left side.
                idt2 = patternNode(String.valueOf(node)); // right side.
                Pattern pattern1 = Pattern.compile(idt1.toString(), Pattern.CASE_INSENSITIVE);
                Matcher match1 = pattern1.matcher(idt1.toString());
                boolean match1Found = match1.find();
                Pattern pattern2 = Pattern.compile(idt2.getValue(), Pattern.CASE_INSENSITIVE);
                Matcher match2 = pattern2.matcher(idt2.getValue());
                boolean match2Found = match2.find();
                if (match1Found != match2Found) {
                    result = "matches";
                }
                else {
                    result = "does not match";
                }
            }
            // end of match and not match operations.
            // start of dollar operation.
            else if (node.toString().equals(OperationNode.Operations.DOLLAR.toString())) {
                idt1 = getIDT(node, variables);
                result = String.valueOf(new OperationNode(idt1, OperationNode.Operations.ADD, Optional.of(node)));
            }
            // end of dollar operation.
            // start of unary operations.
            else if (node.toString().equals(OperationNode.Operations.UNARYPOS.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    result = String.valueOf(+num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The unary operation couldn't be interpreted.");
                }
            }
            else if (node.toString().equals(OperationNode.Operations.UNARYNEG.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    result = String.valueOf(-num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The unary operation couldn't be interpreted.");
                }
            }
            // end of unary operations.
            // start of pre- and post-increment operations.
            else if (node.toString().equals(OperationNode.Operations.PREINC.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    result = String.valueOf(++num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The pre-increment operation couldn't be interpreted.");
                }
            }
            else if (node.toString().equals(OperationNode.Operations.POSTINC.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num1++;
                    result = String.valueOf(num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The post-increment operation couldn't be interpreted.");
                }
            }
            // end of pre- and post-increment operations.
            // start of pre- and post-decrement operations.
            else if (node.toString().equals(OperationNode.Operations.PREDEC.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    result = String.valueOf(--num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The pre-decrement operation couldn't be interpreted.");
                }
            }
            else if (node.toString().equals(OperationNode.Operations.POSTDEC.toString())) {
                idt1 = getIDT(node, variables);
                try {
                    num1 = Float.parseFloat(String.valueOf(idt1));
                    num1--;
                    result = String.valueOf(num1);
                }
                catch (Exception e) {
                    System.out.println("Error. The post-decrement operation couldn't be interpreted.");
                }
            }
            // end of pre- and post-decrement operations.
            // start of concatenation.
            else if (node.toString().equals(OperationNode.Operations.CONCATENATION.toString())) {
                idt1 = getIDT(node, variables);
                idt2 = getIDT(node, variables);
                result = String.valueOf(idt1).concat(String.valueOf(idt2));
            }
            // end of concatenation.
            // start of IN operation.
            else if (node.toString().equals(OperationNode.Operations.IN.toString())) {
                idt2 = getIDT(node, variables);
                if (idt2.equals(variableReferenceNode(String.valueOf(idt2)))) {
                    idt1 = getIDT(node, variables);
                    if (variables.containsKey(idt1.getValue()) || getGlobalVariableStorage().containsKey(idt1.getValue())) {
                        result = "1";
                    }
                    else {
                        result = "0";
                    }
                }
                else {
                    throw new Exception();
                }
            }
            // end of IN operation.
        }
        catch (Exception e) {
            System.out.println("Error. The IN operation could not be interpreted.");
        }
        return new InterpreterDataType(result);
    }


    /**
     * this constantNode() method returns a new InterpreterDataType with a value that's set to the constant node's value.
     * @param value the value.
     * @return a new InterpreterDataType with the value set to the constant node's value.
     */

    public InterpreterDataType constantNode(int value) {
        return new InterpreterDataType(String.valueOf(value));
    }

    /**
     * this patternNode() method handles errors when someone tried to pass a pattern to a function or assignment.
     * @param patternNode the pattern node being passed into a function or assignment as a string.
     * @throws Exception throws an error when someone tries to pass a pattern to a function or assignment.
     */
    public InterpreterDataType patternNode(String patternNode) throws Exception {
        throw new Exception();
    }

    /**
     * this assignmentNode() method checks to see if the target is a variable which could be a variable reference or an operation.
     * @param target the target InterpreterDataType.
     * @return the result.
     */

    public InterpreterDataType assignmentNode(InterpreterDataType target) throws Exception {
        InterpreterDataType result = null;
        if (target.toString().equals(OperationNode.Operations.DOLLAR.name()) || target.toString().equals(new VariableReferenceNode(String.valueOf(target), Optional.of(target)))) {
            result = getIDT(target,null); // calls getIDT() on the right side of the assignment if there isn't a left side.
        }
        return result;
    }

    /**
     * this functionCallNode() method calls the runFunctionCall() method.
     * @return the result of the runFunctionCall() method as an InterpreterDataType.
     */

    public InterpreterDataType functionCallNode() throws Exception {
        String idt = runFunctionCall(null, getGlobalVariableStorage());
        return new InterpreterDataType(idt);
    }

    /**
     * this runFunctionCall() method takes a function call node and locals.
     * @param functionCallNode the function call node being called.
     * @param variableStorage the local variables.
     * @return an empty string, for now.
     */

    public String runFunctionCall(FunctionCallNode functionCallNode, HashMap<String, InterpreterDataType> variableStorage) throws Exception {
        // start of the mapping process for non-variadics.
        FunctionDefinitionNode function = (FunctionDefinitionNode) functionCallNode.getFunctionCallNode();
        LinkedList<StatementNode> statement = new LinkedList<>();
        ArrayList<FunctionCallNode> functionCallNodeArray = new ArrayList<>();
        for (int i = 0; i < functionCallNode.getParameters().size(); i++) {
            if (function.getParameterName() != functionCallNode.getParameters().get(i)) {
                throw new Exception();
            }
        }
        HashMap<String, InterpreterDataType> map = new HashMap<>(); // map of defined functions.
        for (Node f : functionCallNode.getParameters()) {
            map.put(String.valueOf(f), getIDT(f, variableStorage));
        }
        if (function instanceof BuiltInFunctionDefinitionNode) {
            for (int i = 0; i < function.getFunctionName().length(); i++) {
                statement.add(i, function.getStatementNode(i));
                // start of the mapping process for variadics.
                if (statement.getLast() != null) {
                    functionCallNodeArray.add(functionCallNode);
                    ((BuiltInFunctionDefinitionNode) function).Execute.apply(map);
                }
                // end of the mapping process for variadics.
            }
            interpretListOfStatements(statement, map);
            // end of the mapping process for non-variadics.
        }
        else {
            throw new Exception();
        }
        return "";
    }

    /**
     * this ternaryNode() method evaluates the boolean condition using the getIDT() method for both sides.
     * @param idt the condition.
     * @param variableStorage the variables being used.
     * @return true if the evaluation succeeded, false otherwise.
     */

    public boolean ternaryNode(InterpreterDataType idt, HashMap<String, InterpreterDataType> variableStorage) throws Exception {
        Node leftSideCondition = getIDT(idt, variableStorage);
        Node rightSideCondition;
        if (leftSideCondition != null) {
            rightSideCondition = getIDT(idt, variableStorage);
            if (rightSideCondition != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * this variableReferenceNode() method checks to see if it's an array reference or not.
     * @param arrayReference the array reference.
     * @return the variable if it isn't an array reference after looking it up in the global or local variable storage.
     * But if it is an array reference, it tries to resolve the index, and then it looks up that index in the hashmap for the variable's.
     * @throws Exception throws an exception if the variable is not an InterpreterArrayDataType.
     */

    public InterpreterArrayDataType variableReferenceNode(String arrayReference) throws Exception {
        InterpreterDataType idat = null;
        if (arrayReference == null) {
            setGlobalVariableStorage(arrayReference, idat);
            idat = getGlobalVariableStorage().get(arrayReference);
            return (InterpreterArrayDataType) idat;
        }
        else if (arrayReference != null) {
            idat = getGlobalVariableStorage().get(arrayReference);
            return (InterpreterArrayDataType) idat;
        }
        else {
            throw new Exception();
        }
    }

    /**
     * this populateFunctionHashMap() method populates the functionSource hashmap
     * with the types of functions from the program node and each of the builtins.
     */
    public void populateFunctionHashMap() {
        setFunctionSource("andThen", getAndThen());
        setFunctionSource("apply", getApply());
        setFunctionSource("compose", getCompose());
        setFunctionSource("identity", getIdentity());
    }

    /**
     * this populateGlobalVariablesHashMap() method populates the globalVariablesStorage() hashmap
     * with the global variables that will be used.
     */

    public void populateGlobalVariablesHashMap() {
        setGlobalVariableStorage("FS", getFS());
        setGlobalVariableStorage("NF", getNF());
        setGlobalVariableStorage("NR", getNR());
        setGlobalVariableStorage("FNR", getFNR());
        setGlobalVariableStorage("FILENAME", getFILENAME());
        setGlobalVariableStorage("OFMT", getOFMT());
        setGlobalVariableStorage("OFS", getOFS());
        setGlobalVariableStorage("ORS", getORS());
    }

    /**
     * this Interpreter() constructor takes a program node and a string and tries to create a LineManager object with them.
     * It also sets the FILENAME, FS, OFMT, OFS and ORS global variables to a default value, and it also populates the hashmaps.
     * @param node a program node.
     * @param filePath a file path.
     */

    public Interpreter(ProgramNode node, String filePath) {
        setProgramNode(node);
        LineManager lineManager = null;
        try {
            if (!filePath.isEmpty()) {
                lineManager = new LineManager(Files.readAllLines(Paths.get(filePath)));
            }
            else {
                lineManager = new LineManager(Collections.emptyList());
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong with initializing the interpreter.");
        }
        if (lineManager != null) {
            setFILENAME(lineManager.toString());
        }
        setFS(" ");
        setOFMT("%.6g");
        setOFS(" ");
        setORS("\n");
        populateGlobalVariablesHashMap();
        populateFunctionHashMap();
        printf();
        print();
        getLine();
        next();
        gSub();
        match();
        sub();
        index();
        length();
        split();
        subString();
        toLower();
        toUpper();
    }



    /**
     * this toUpper() method has the instance of the toUpper built-in function with the execute lambda function using the Java string class.
     */

    public void toUpper() {
        BuiltInFunctionDefinitionNode toUpper = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        String toUpperString = toUpper.toString().toUpperCase();
        if (toUpperString.equals(toUpper.toString())) {
            toUpper.Execute = this::toUpperImplementation;
        }
    }

    /**
     * this toLower() method has the instance of the toLower built-in function with the execute lambda function using the Java string class.
     */

    public void toLower() {
        BuiltInFunctionDefinitionNode toLower = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        String toLowerString = toLower.toString().toLowerCase();
        if (toLowerString.equals(toLower.toString())) {
            toLower.Execute = this::toLowerImplementation;
        }
    }

    /**
     * this subString() method has the instance of the subString built-in function with the execute lambda function using the Java string class.
     */

    public void subString() {
        BuiltInFunctionDefinitionNode subString = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        String subStringString = subString.toString().substring(0, Integer.parseInt(String.valueOf(subString.toString().length())));
        if (subStringString.equals(subString.toString())) {
            subString.Execute = this::subStringImplementation;
        }
    }

    /**
     * this split() method has the instance of the split built-in function with the execute lambda function using the Java string class.
     */

    public void split() {
        BuiltInFunctionDefinitionNode split = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        String splitString = Arrays.toString(split.toString().split(split.toString()));
        if (splitString.equals(split.toString())) {
            split.Execute = this::splitImplementation;
        }
    }

    /**
     * this length() method has the instance of the length built-in function with the execute lambda function using the Java string class.
     */

    public void length() {
        BuiltInFunctionDefinitionNode length = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        int len = length.toString().length();
        String lengthNumber = String.valueOf(Integer.parseInt(String.valueOf(len)));
        if (lengthNumber.equals(String.valueOf(len))) {
            length.Execute = this::lengthImplementation;
        }
    }

    /**
     * this index() method has the instance of the length built-in function with the execute lambda function using the Java string class.
     */

    public void index() {
        BuiltInFunctionDefinitionNode index = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        int number = index.toString().indexOf(index.toString());
        String indexNumber = String.valueOf(Integer.parseInt(String.valueOf(number)));
        if (indexNumber.equals(String.valueOf(number))) {
            index.Execute = this::indexImplementation;
        }
    }

    /**
     * this sub() method has the instance of the sub built-in function with the execute lambda function using Java regex.
     */

    public void sub() {
        BuiltInFunctionDefinitionNode sub = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        Pattern patternSub = Pattern.compile(sub.toString(), Pattern.CASE_INSENSITIVE);
        Matcher matcherSub = patternSub.matcher(sub.toString());
        boolean foundSub = matcherSub.find();
        if (foundSub) {
            sub.Execute = this::subImplementation;
        }
    }

    /**
     * this match() method has the instance of the match built-in function with the execute lambda function using Java regex.
     */

    public void match() {
        BuiltInFunctionDefinitionNode match = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        Pattern patternMATCH = Pattern.compile(match.toString(), Pattern.CASE_INSENSITIVE);
        Matcher matcherMATCH = patternMATCH.matcher(match.toString());
        boolean foundMatch = matcherMATCH.find();
        if (foundMatch) {
            match.Execute = this::matchImplementation;
        }
    }

    /**
     * this gSub() method has the instance of the gsub built-in function with the execute lambda function using Java regex.
     */

    public void gSub() {
        BuiltInFunctionDefinitionNode gsub = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        Pattern patternGSUB = Pattern.compile(gsub.toString(), Pattern.CASE_INSENSITIVE);
        Matcher matcherGSUB = patternGSUB.matcher(gsub.toString());
        boolean foundGSUB = matcherGSUB.find();
        if (foundGSUB) {
            gsub.Execute = this::gsubImplementation;
        }
    }

    /**
     * this next() method has the instance of the next built-in function with the execute lambda function using splitAndAssign().
     */

    public void next() {
        BuiltInFunctionDefinitionNode next = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        boolean checkNext = lineManager.splitAndAssign();
        if (checkNext) {
            next.Execute = this::nextImplementation;
        }
    }

    /**
     * this getLine() method has the instance of the getline built-in function with the execute lambda function using splitAndAssign().
     */
    public void getLine() {
        BuiltInFunctionDefinitionNode getLine = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        boolean checkLine = lineManager.splitAndAssign();
        if (checkLine) {
            getLine.Execute = this::lineImplementation;
        }
    }

    /**
     * this printf() method has the instance of the printf built-in function with the execute lambda function.
     * It also uses the variadic boolean from the BuiltInFunctionDefinitionNode to indicate that it is variadic.
     */
    public void printf() {
        BuiltInFunctionDefinitionNode printf = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        printf.setIsVariadic(true);
        try {
            if (printf.getIsVariadic()) {
                System.out.printf("%s", printf.Execute = this::printfImplementation);
            }
        }
        catch (Exception e) {
            System.out.println("Error. The printf built-in function could not be instantiated.");
        }
    }

    /**
     * this print() method has the instance of the print built-in function with the execute lambda function.
     * It also uses the variadic boolean from the BuiltInFunctionDefinitionNode to indicate that it is variadic.
     */

    public void print() {
        BuiltInFunctionDefinitionNode print = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        print.setIsVariadic(true);
        if (print.getIsVariadic()) {
            System.out.print(print.Execute = this::printImplementation);
        }
    }

    /**
     * this sprintf() method has the instance of the sprintf built-in function with the execute lambda function.
     */

    public void sprintf() {
        BuiltInFunctionDefinitionNode sprintf = new BuiltInFunctionDefinitionNode(getFunctionSource().toString(), null, null);
        sprintf.setIsVariadic(true);
        try {
            if (sprintf.getIsVariadic()) {
                System.out.printf("%s", sprintf.Execute = this::sprintfImplementation);
            }
        }
        catch (Exception e) {
            System.out.println("Error. The sprintf built-in function could not be instantiated.");
        }
    }

    /**
     * this printfImplementation() method has the implementation for the printf built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */
    private String printfImplementation(HashMap<String,InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this printImplementation() method has the implementation for the print built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String printImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this lineImplementation() method has the implementation for the getline built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String lineImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this nextImplementation() method has the implementation for the next built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String nextImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this gsubImplementation() method has the implementation for the gsub built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String gsubImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this matchImplementation() method has the implementation for the match built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String matchImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this subImplementation() method has the implementation for the sub built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String subImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this indexImplementation() method has the implementation for the index built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String indexImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this lengthImplementation() method has the implementation for the length built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String lengthImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this splitImplementation() method has the implementation for the split built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String splitImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this subStringImplementation() method has the implementation for the substring built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String subStringImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this toLowerImplementation() method has the implementation for the tolower built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String toLowerImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this toUpperImplementation() method has the implementation for the isupper built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */

    private String toUpperImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }

    /**
     * this sprintfImplementation() method has the implementation for the sprintf built-in function.
     * @param params the parameters of the function.
     * @return a string.
     */
    private String sprintfImplementation(HashMap<String, InterpreterDataType> params) {
        return params.toString();
    }
    /**
     * this getAndThen() method gets the andThen global variable.
     * @return the andThen global variable.
     */

    public FunctionDefinitionNode getAndThen() {
        return Interpreter.andThen;
    }

    /**
     * this setAndThen() method sets the andThen global variable as a FunctionDefinitionNode.
     * @param string the value of the andThen global variable as a string.
     */

    public void setAndThen(String string) {
        Interpreter.andThen = new FunctionDefinitionNode(string, null, null);
    }

    /**
     * this getApply() method gets the apply global variable.
     * @return the apply global variable.
     */

    public FunctionDefinitionNode getApply() {
        return Interpreter.apply;
    }

    /**
     * this setApply() method sets the apply global variable as a FunctionDefinitionNode.
     * @param string the value of the apply global variable as a string.
     */

    public void setApply(String string) {
        Interpreter.apply = new FunctionDefinitionNode(string, null, null);
    }

    /**
     * this getCompose() method gets the compose global variable.
     * @return the compose global variable.
     */

    public FunctionDefinitionNode getCompose() {
        return Interpreter.compose;
    }

    /**
     * this setCompose() method sets the compose global variable as a FunctionDefinitionNode.
     * @param string the value of the compose global variable as a string.
     */

    public void setCompose(String string) {
        Interpreter.compose = new FunctionDefinitionNode(string, null, null);
    }

    /**
     * this getIdentity() method gets the identity global variable.
     * @return the identity global variable.
     */

    public FunctionDefinitionNode getIdentity() {
        return Interpreter.identity;
    }

    /**
     * this setIdentity() method sets the identity global variable as a FunctionDefinitionNode.
     * @param string the value of the identity global variable as a string.
     */

    public void setIdentity(String string) {
        Interpreter.identity = new FunctionDefinitionNode(string, null, null);
    }

    /**
     * this getFILENAME() method gets the FILENAME global variable.
     * @return the FILENAME global variable.
     */

    public InterpreterDataType getFILENAME() {
        return Interpreter.FILENAME;
    }

    /**
     * this setFILENAME() method sets the FILENAME global variable as an InterpreterDataType.
     * @param string the value of the FILENAME global variable as a string.
     */

    public void setFILENAME(String string) {
        Interpreter.FILENAME = new InterpreterDataType(string);
    }

    /**
     * this getOFMT() method gets the OFMT global variable.
     * @return the OFMT global variable.
     */

    public InterpreterDataType getOFMT() {
        return Interpreter.OFMT;
    }

    /**
     * this setOFMT() method sets the OFMT global variable as an InterpreterDataType.
     * @param string the value of the OFMT global variable as a string.
     */

    public void setOFMT(String string) {
        Interpreter.OFMT = new InterpreterDataType(string);
    }

    /**
     * this getOFS() method gets the OFS global variable.
     * @return the OFS global variable.
     */

    public InterpreterDataType getOFS() {
        return Interpreter.OFS;
    }

    /**
     * this setOFS() method sets the OFS global variable as an InterpreterDataType.
     * @param string the value of the OFS global variable as a string.
     */

    public void setOFS(String string) {
        Interpreter.OFS = new InterpreterDataType(string);
    }

    /**
     * this getORS() method gets the ORS global variable.
     * @return the ORS global variable.
     */

    public InterpreterDataType getORS() {
        return Interpreter.ORS;
    }

    /**
     * this setORS() method sets the ORS global variable as an InterpreterDataType.
     * @param string the value of the ORS global variable as a string.
     */

    public void setORS(String string) {
        Interpreter.ORS = new InterpreterDataType(string);
    }

    /**
     * this getFS() method gets the FS global variable.
     * @return the FS global variable.
     */

    public InterpreterDataType getFS() {
        return Interpreter.FS;
    }

    /**
     * this setFS() method sets the FS global variable as an InterpreterDataType.
     * @param string the value of the FS global variable as a string.
     */

    public void setFS(String string) {
        Interpreter.FS = new InterpreterDataType(string);
    }

    /**
     * this getNF() method gets the NF global variable.
     * @return the NF global variable.
     */

    public InterpreterDataType getNF() {
        return Interpreter.NF;
    }

    /**
     * this setNF() method sets the NF global variable as an InterpreterDataType.
     * @param string the value of the NF global variable as a string.
     */

    public void setNF(String string) {
        Interpreter.NF = new InterpreterDataType(string);
    }

    /**
     * this getNR() method gets the NR global variable.
     * @return the NR global variable.
     */

    public InterpreterDataType getNR() {
        return Interpreter.NR;
    }

    /**
     * this setNR() method sets the NR global variable as an InterpreterDataType.
     * @param string the value of the NR data type as a string.
     */

    public void setNR(String string) {
        Interpreter.NR = new InterpreterDataType(string);
    }

    /**
     * this getFR() method gets the FNR global variable.
     * @return the FNR global variable.
     */

    public InterpreterDataType getFNR() {
        return Interpreter.FNR;
    }

    /**
     * this setFNR() method sets the FNR global variable as an InterpreterDataType.
     * @param string the value of the NFR global variable as a string.
     */

    public void setFNR(String string) {
        Interpreter.FNR = new InterpreterDataType(string);
    }

    /**
     * this setFile() method stores the file to the list.
     * @param file adds the file to the list.
     */

    public void setFile(List<String> file) {
        this.file.add(file.toString());
    }

    /**
     * this getFile() method gets the list that has the file.
     * @return the file list.
     */

    public List<String> getFile() {
        return file;
    }

    /**
     * this setGlobalVariableStorage() method puts the global variables into the hashmap.
     * @param string the value of the global variables as strings.
     * @param idt the global variables as InterpreterDataTypes.
     */

    public void setGlobalVariableStorage(String string, InterpreterDataType idt) {
        globalVariableStorage.put(string, idt);
    }

    /**
     * this getGlobalVariableStorage() gets the global variables from the hashmap.
     * @return the hashmap that has the global variables.
     */

    public HashMap<String, InterpreterDataType> getGlobalVariableStorage() {
        return globalVariableStorage;
    }

    /**
     * this setFunctionSource() method puts the types of functions
     * from the program node and each of the builtins into the hashmap.
     * @param string the value of the functions as a string.
     * @param function the functions as FunctionDefinitionNodes.
     */

    public void setFunctionSource(String string, FunctionDefinitionNode function) {
        functionSource.put(string, function);
    }

    /**
     * this getFunctionSource() method gets the types of functions
     * from the program node and each of the builtins from the hashmap.
     * @return the hashmap that has the types of functions.
     */

    public HashMap<String, FunctionDefinitionNode> getFunctionSource() {
        return functionSource;
    }

    class LineManager {

        // this inner class LineManager manages each line of text from the file as the Interpreter runs.

        /**
         * this LineManager() constructor takes a list and stores it inside file using the setFile() method.
         * @param list the input file as a list.
         */

        public LineManager(List<String> list) {
            setFile(list);
        }

        /**
         * this splitAndAssign() method tries to find the global variables in the file, splits the string
         * and assign values to the global variables.
         * @return true if the method was able to find the global variables, split the string and assign
         * values to the global variables, false otherwise.
         */

        public boolean splitAndAssign() {
            if (getFile().contains("FS")) {
                for (int i = 0; i < getFile().size(); i++) {
                    List<String> file = Arrays.asList(getFile().get(i).split("FS"));
                    if (file.get(i).equals("FS")) {
                        setFS("$0");
                    }
                }
                return true;
            }
            else if (getFile().contains("NF")) {
                for (int i = 0; i < getFile().size(); i++) {
                    List<String> file = Arrays.asList(getFile().get(i).split("NF"));
                    if (file.get(i).equals("NF")) {
                        setNF("$1");
                    }
                }
                return true;
            }
            else if (getFile().contains("NR")) {
                for (int i = 0; i < getFile().size(); i++) {
                    List<String> file = Arrays.asList(getFile().get(i).split("NR"));
                    if (file.get(i).equals("NR")) {
                        setNR("$2");
                    }
                }
                return true;
            }
            else if (getFile().contains("FNR")) {
                for (int i = 0; i < getFile().size(); i++) {
                    List<String> file = Arrays.asList(getFile().get(i).split("FNR"));
                    if (file.get(i).equals("FNR")) {
                        setFNR("$3");
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }

    }
}
