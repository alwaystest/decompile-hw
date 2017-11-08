package org.apache.commons.jexl2.parser;

public class ASTIfStatement extends JexlNode {
    public ASTIfStatement(int id) {
        super(id);
    }

    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
