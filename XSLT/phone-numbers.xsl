<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>Friend's numbers</h1>
        <ul><xsl:apply-templates/></ul>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="contact[@type='friends']">
    <li>
      <xsl:choose>
        <xsl:when test="starts-with(number,'(01273)')">
          <span style="color:red"><xsl:value-of select="number"/></span>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="number"/>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:text>  </xsl:text>
      <xsl:value-of select="name"/>
    </li>
  </xsl:template>
  <xsl:template match="text()"/>
</xsl:stylesheet>
