package org.apache.commons.jexl2.parser;

public class ASTGENode extends JexlNode {
    public ASTGENode(int id) {
        super(id);
    }

    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
