<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:y="http://www.yworks.com/xml/graphml"
 xmlns:g="http://graphml.graphdrawing.org/xmlns">

<xsl:output method="xml" indent="yes"/>

  <xsl:template match="JDepend">

    <g:graphml xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd">
      <g:key id="d0" for="node" yfiles.type="nodegraphics"/>
      <g:key id="d1" for="edge" yfiles.type="edgegraphics"/>

      <g:key attr.name="TotalClasses" attr.type="int" for="node" id="d4">
        <g:default>0</g:default>
      </g:key>
      <g:key attr.name="ConcreteClasses" attr.type="int" for="node" id="d5">
        <g:default>0</g:default>
      </g:key>
      <g:key attr.name="AbstractClasses" attr.type="int" for="node" id="d6">
        <g:default>0</g:default>
      </g:key>
      <g:key attr.name="Ca" attr.type="int" for="node" id="d7">
        <g:default>0</g:default>
      </g:key>
      <g:key attr.name="Ce" attr.type="int" for="node" id="d8">
        <g:default>0</g:default>
      </g:key>
      <g:key attr.name="A" attr.type="double" for="node" id="d9">
        <g:default>0.0</g:default>
      </g:key>
      <g:key attr.name="I" attr.type="double" for="node" id="d10">
        <g:default>0.0</g:default>
      </g:key>
      <g:key attr.name="D" attr.type="double" for="node" id="d11">
        <g:default>0.0</g:default>
      </g:key>
      <g:key attr.name="V" attr.type="double" for="node" id="d12">
        <g:default>0.0</g:default>
      </g:key>

      <g:graph id="G" edgedefault="directed">

        <xsl:apply-templates select="Packages"/>

      </g:graph>
    </g:graphml>

  </xsl:template>

  <xsl:template match="/JDepend/Packages">
    <xsl:apply-templates select="Package" mode="nodes"/>
    <xsl:apply-templates select="Package" mode="edges"/>
  </xsl:template>

  <xsl:template match="/JDepend/Packages/Package" mode="nodes">
    <xsl:element name="g:node">
      <xsl:attribute name="id">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <g:data key="d0">
        <y:ShapeNode configuration="DemoDefaults#Node">
          <y:Shape type="rectangle"/>
          <y:Geometry height="30.0" width="30.0" x="0.0" y="0.0"/>
          <xsl:element name="y:Fill">
            <xsl:attribute name="color">
              <xsl:text>#</xsl:text>
              <xsl:call-template name="newColor"/>
            </xsl:attribute>
            <xsl:attribute name="transparent">false</xsl:attribute>
          </xsl:element>
          <y:BorderStyle color="#000000" type="line" width="1.0"/>
          <y:NodeLabel>
            <xsl:value-of select="@name"/>
          </y:NodeLabel>
        </y:ShapeNode>
      </g:data>

      <xsl:apply-templates select="Stats" mode="nodes"/>
    </xsl:element>
  </xsl:template>

  <xsl:template match="/JDepend/Packages/Package/Stats" mode="nodes">
    <g:data key="d4"><xsl:value-of select="TotalClasses"/></g:data>
    <g:data key="d5"><xsl:value-of select="ConcreteClasses"/></g:data>
    <g:data key="d6"><xsl:value-of select="AbstractClasses"/></g:data>
    <g:data key="d7"><xsl:value-of select="Ca"/></g:data>
    <g:data key="d8"><xsl:value-of select="Ce"/></g:data>
    <g:data key="d9"><xsl:value-of select="A"/></g:data>
    <g:data key="d10"><xsl:value-of select="I"/></g:data>
    <g:data key="d11"><xsl:value-of select="D"/></g:data>
    <g:data key="d12"><xsl:value-of select="V"/></g:data>
  </xsl:template>


  <xsl:template match="/JDepend/Packages/Package" mode="edges">
    <xsl:apply-templates select="DependsUpon" mode="edges"/>
  </xsl:template>

  <xsl:template match="/JDepend/Packages/Package/DependsUpon" mode="edges">
    <xsl:apply-templates select="Package" mode="edges"/>
  </xsl:template>

  <xsl:template match="/JDepend/Packages/Package/DependsUpon/Package" mode="edges">
    <xsl:element name="g:edge">
      <xsl:attribute name="source">
        <xsl:value-of select="../../@name"/>
      </xsl:attribute>
      <xsl:attribute name="target">
        <xsl:value-of select="."/>
      </xsl:attribute>
      <g:data key="d1">
        <y:PolyLineEdge>
          <y:Arrows source="none" target="standard"/>
        </y:PolyLineEdge>
      </g:data>
    </xsl:element>
  </xsl:template>


  <xsl:template name="newColor">
    <xsl:choose>
      <xsl:when test="Stats/D">
        <xsl:text>E5</xsl:text>
        <xsl:variable name="D" select="Stats/D"/>
        <xsl:call-template name="toHex">
          <xsl:with-param name="index">
            <xsl:value-of select="round(229 * (1 - number($D)))"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:call-template name="toHex">
          <xsl:with-param name="index">
            <xsl:value-of select="round(229 * (1 - number($D)))"/>
          </xsl:with-param>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>E5E5E5</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="toHex">
    <xsl:param name="index" />
    <xsl:if test="$index > 0">
      <xsl:call-template name="toHex">
        <xsl:with-param name="index" select="floor($index div 16)" />
      </xsl:call-template>
      <xsl:choose>
        <xsl:when test="$index mod 16 &lt; 10">
          <xsl:value-of select="$index mod 16" />
        </xsl:when>
        <xsl:otherwise>
          <xsl:choose>
            <xsl:when test="$index mod 16 = 10">A</xsl:when>
            <xsl:when test="$index mod 16 = 11">B</xsl:when>
            <xsl:when test="$index mod 16 = 12">C</xsl:when>
            <xsl:when test="$index mod 16 = 13">D</xsl:when>
            <xsl:when test="$index mod 16 = 14">E</xsl:when>
            <xsl:when test="$index mod 16 = 15">F</xsl:when>
            <xsl:otherwise>A</xsl:otherwise>
          </xsl:choose>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:if>
  </xsl:template>

</xsl:stylesheet> 
