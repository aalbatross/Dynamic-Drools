/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.event;

import java.util.Collection;
import org.drools.definition.KnowledgePackage;

/**
 *
 * @author Ravi
 */
public class KnowledgeEvent {
   private String type;
   private Collection<KnowledgePackage> newPackage;
   private String[] removeRule;

   public KnowledgeEvent(String type,Collection<KnowledgePackage> newPackage,String[] rules)
   {
       this.type=type;
       this.newPackage=newPackage;
       this.removeRule=rules;
   }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the newPackage
     */
    public Collection<KnowledgePackage> getNewPackage() {
        return newPackage;
    }

    /**
     * @param newPackage the newPackage to set
     */
    public void setNewPackage(Collection<KnowledgePackage> newPackage) {
        this.newPackage = newPackage;
    }

    /**
     * @return the removeRule
     */
    public String[] getRemoveRule() {
        return removeRule;
    }

    /**
     * @param removeRule the removeRule to set
     */
    public void setRemoveRule(String[] removeRule) {
        this.removeRule = removeRule;
    }
   
   
}
