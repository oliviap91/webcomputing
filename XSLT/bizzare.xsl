<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="*">
    <xsl:element name="{translate(name(),'Ee','Yy')}">
      <xsl:apply-templates select="@*"/>
      <xsl:apply-templates/>
    </xsl:element>
  </xsl:template>

  <xsl:template match="@*">
    <xsl:attribute name="{translate(name(),'Ee','Yy')}">
      <xsl:value-of select="."/>
    </xsl:attribute>
  </xsl:template>


</xsl:stylesheet>
