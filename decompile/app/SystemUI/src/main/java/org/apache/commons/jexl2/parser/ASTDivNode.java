package org.apache.commons.jexl2.parser;

public class ASTDivNode extends JexlNode {
    public ASTDivNode(int id) {
        super(id);
    }

    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
