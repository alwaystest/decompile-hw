package org.apache.commons.jexl2.parser;

public class ASTLTNode extends JexlNode {
    public ASTLTNode(int id) {
        super(id);
    }

    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
