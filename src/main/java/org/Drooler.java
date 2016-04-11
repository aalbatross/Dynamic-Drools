/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org;

import javax.swing.JOptionPane;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.StatelessRuleSession;

/**
 *
 * @author Ravi
 */
public class Drooler {
    
    private String[] ruleSource;
    private KnowledgeBuilder kbuilder;
    private KnowledgeBase kbase;
    private StatelessKnowledgeSession ksession;
    
    public Drooler(String[] initRules)
    {
        try
        {
            this.ruleSource=initRules;
            this.kbuilder= KnowledgeBuilderFactory.newKnowledgeBuilder();
            for(int i=0;i<initRules.length;i++)
            {                
                kbuilder.add( ResourceFactory.newFileResource(initRules[i] ),ResourceType.DRL);
            }
            if(kbuilder.hasErrors())
            {
                JOptionPane.showMessageDialog(null, kbuilder.getErrors());
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Initially Compiled Successfully!!!");
            }
            this.kbase=KnowledgeBaseFactory.newKnowledgeBase();
            this.kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
            this.ksession=this.kbase.newStatelessKnowledgeSession();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }
    }

    /**
     * @return the ruleSource
     */
    public String[] getRuleSource() {
        return ruleSource;
    }

    /**
     * @param ruleSource the ruleSource to set
     */
    public void setRuleSource(String[] ruleSource) {
        this.ruleSource = ruleSource;
    }

    /**
     * @return the kbuilder
     */
    public KnowledgeBuilder getKbuilder() {
        return kbuilder;
    }

    /**
     * @param kbuilder the kbuilder to set
     */
    public void setKbuilder(KnowledgeBuilder kbuilder) {
        this.kbuilder = kbuilder;
    }

    /**
     * @return the kbase
     */
    public KnowledgeBase getKbase() {
        return kbase;
    }

    /**
     * @param kbase the kbase to set
     */
    public void setKbase(KnowledgeBase kbase) {
        this.kbase = kbase;
    }

	public StatelessKnowledgeSession getKsession() {
		return ksession;
	}

	public void setKsession(StatelessKnowledgeSession ksession) {
		this.ksession = ksession;
	}
   
    
}
