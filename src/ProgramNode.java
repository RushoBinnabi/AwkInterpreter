/**
 * Name: Rusho Binnabi
 * Date: 9/15/2023
 * Assignment: Parser 1
 * Class: ICSI 311 - Fall 2023
 */

import java.util.LinkedList;

public class ProgramNode extends Node {
    // this ProgramNode class the root node of the tree. The ProgramNode will have the input program
    // as a tree from a linked list of tokens.
    private final LinkedList<FunctionDefinitionNode> functionDefinitionNode = new LinkedList<>();
    private final LinkedList<BlockNode> startBlock = new LinkedList<>();
    private final LinkedList<BlockNode> endBlock = new LinkedList<>();
    private final LinkedList<BlockNode> block = new LinkedList<>();

    /**
     * this setStartBlock() method sets the block node of the tree.
     * @param b the block node to be added to the linked list.
     */
    public void setStartBlock(BlockNode b) {
        startBlock.add(b);
    }

    /**
     * this getStartBlock() method gets the block node from the tree.
     * @param i the block node that will be returned from the linked list.
     * @return a block node.
     */

    public BlockNode getStartBlock(int i) {
        return startBlock.get(i);
    }

    /**
     * this setFunctionDefinitionNode() method sets the function definition node of the tree.
     * @param f the function definition node to be added to the linked list.
     */

    public void setFunctionDefinitionNode(FunctionDefinitionNode f) {
        functionDefinitionNode.add(f);
    }

    /**
     * this getFunctionDefinitionNode() method gets the function definition node from the tree.
     * @param i the function definition node that will be returned from the linked list.
     * @return a function definition node.
     */

    public FunctionDefinitionNode getFunctionDefinitionNode(int i) {
        return functionDefinitionNode.get(i);
    }

    /**
     * this setEndBlock() method sets the end block node of the tree.
     * @param b the end block node to be added to the linked list.
     */

    public void setEndBlock(BlockNode b) {
        endBlock.add(b);
    }

    /**
     * this getEndBlock() method gets the end block from the tree.
     * @param i the end block node that will be returned from the linked list.
     * @return a end block node.
     */

    public BlockNode getEndBlock(int i) {
        return endBlock.get(i);
    }

    /**
     * this setBlock() method sets the block node of the tree.
     * @param b the block node to be added to the linked list.
     */

    public void setBlock(BlockNode b) {
        block.add(b);
    }

    /**
     * this getBlock() method gets the block node from the tree.
     * @param i the block node that will be returned from the linked list.
     * @return a block node.
     */

    public BlockNode getBlock(int i) {
        return block.get(i);
    }

    /**
     * this toString() method creates a string representation of the ProgramNode.
     * @return a string representation of the ProgramNode.
     */

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (FunctionDefinitionNode node : functionDefinitionNode) {
            output.append("FunctionDefinitionNode( ").append(node).append(")").append("+");
        }
        for (BlockNode start : startBlock) {
            output.append("StartBlock( ").append(start).append(")").append("+");
        }
        for (BlockNode end : endBlock) {
            output.append("EndBlock( ").append(end).append(")").append("+");
        }
        for (BlockNode blocks : block) {
            output.append("StartBlock( ").append(blocks).append(")").append("+");
        }
        return output.toString();
    }


}