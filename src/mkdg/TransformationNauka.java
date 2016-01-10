/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

/**
 *
 * @author Admin
 */
public class TransformationNauka {
    
    private int[][] binaryModel;
    private int[][] structuralElement;
    private TransformationCanvas obrazPrzed;
    
    
    public TransformationNauka(int[][] binaryModelNauka, int[][] structuralElement){
        
        this.binaryModel = binaryModelNauka;
        this.structuralElement = structuralElement;
        
        obrazPrzed = new TransformationCanvas(binaryModel, structuralElement);
       // obrazPrzed.setBounds((przed.getWidth() - (elSize+1)*5)/2, 20, (elSize+1)*5, (elSize+1)*5);
       // przed.add(przedNauka);
        
        

}
    
}
