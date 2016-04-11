/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.event;

/**
 *
 * @author Ravi
 */
public interface KnowledgeChangeListener {
    public void addNewknowledge(KnowledgeEvent ke);
    public void removeKnowledge(KnowledgeEvent ke);
}
