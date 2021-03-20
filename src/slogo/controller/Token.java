package slogo.controller;

/**
 * An enumeration that represents the six overarching categories of syntactic token within the SLogo
 * language.
 *
 * @author Marc Chmielewski
 */
public enum Token {
  COMMENT,
  CONSTANT,
  VARIABLE,
  COMMAND,
  LIST_START,
  GROUP_START,
  COLLECTION_END
}
