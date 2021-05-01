package com.aggieland.database;

/**
 * Various keywords for database queries
 */
public enum DatabaseEnums {

  SELECT("SELECT"),
  STAR("*"),
  FROM("FROM"),
  WHERE("WHERE"),
  ANY("%");

  private final String value;

  DatabaseEnums(String value) {
    this.value = value;
  }

  public String toString() {
    return this.value;
  }

}
