<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<gxl xmlns="http://www.gupro.de/GXL/gxl-1.0.dtd">
    <graph role="graph" edgeids="false" edgemode="directed" id="start">
        <attr name="$version">
            <string>curly</string>
        </attr>
        <node id="n3">
            <attr name="layout">
                <string>301 316 30 16</string>
            </attr>
        </node>
        <node id="n5">
            <attr name="layout">
                <string>478 238 19 19</string>
            </attr>
        </node>
        <node id="n7">
            <attr name="layout">
                <string>107 118 19 19</string>
            </attr>
        </node>
        <node id="n8">
            <attr name="layout">
                <string>124 376 105 32</string>
            </attr>
        </node>
        <node id="n1">
            <attr name="layout">
                <string>556 312 19 19</string>
            </attr>
        </node>
        <node id="n2">
            <attr name="layout">
                <string>180 556 19 19</string>
            </attr>
        </node>
        <node id="n6">
            <attr name="layout">
                <string>650 102 19 19</string>
            </attr>
        </node>
        <node id="n9">
            <attr name="layout">
                <string>436 214 55 32</string>
            </attr>
        </node>
        <node id="n10">
            <attr name="layout">
                <string>472 378 19 19</string>
            </attr>
        </node>
        <node id="n4">
            <attr name="layout">
                <string>422 289 119 64</string>
            </attr>
        </node>
        <node id="n0">
            <attr name="layout">
                <string>132 208 71 32</string>
            </attr>
        </node>
        <edge from="n3" to="n3">
            <attr name="label">
                <string>type:casa</string>
            </attr>
        </edge>
        <edge from="n3" to="n4">
            <attr name="label">
                <string>possui</string>
            </attr>
            <attr name="layout">
                <string>496 -21 316 324 482 321 11</string>
            </attr>
        </edge>
        <edge from="n3" to="n8">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>541 -13 177 392 316 324 11</string>
            </attr>
        </edge>
        <edge from="n3" to="n0">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>328 25 316 324 168 224 11</string>
            </attr>
        </edge>
        <edge from="n5" to="n5">
            <attr name="label">
                <string>int:1</string>
            </attr>
        </edge>
        <edge from="n7" to="n7">
            <attr name="label">
                <string>bool:true</string>
            </attr>
        </edge>
        <edge from="n8" to="n8">
            <attr name="label">
                <string>type:casa</string>
            </attr>
        </edge>
        <edge from="n8" to="n0">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>535 -20 168 224 177 392 11</string>
            </attr>
        </edge>
        <edge from="n8" to="n3">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>541 -13 177 392 316 324 11</string>
            </attr>
        </edge>
        <edge from="n8" to="n2">
            <attr name="label">
                <string>armadilha</string>
            </attr>
            <attr name="layout">
                <string>567 -36 177 392 189 567 11</string>
            </attr>
        </edge>
        <edge from="n1" to="n1">
            <attr name="label">
                <string>bool:false</string>
            </attr>
        </edge>
        <edge from="n2" to="n2">
            <attr name="label">
                <string>bool:true</string>
            </attr>
        </edge>
        <edge from="n6" to="n6">
            <attr name="label">
                <string>int:0</string>
            </attr>
        </edge>
        <edge from="n9" to="n9">
            <attr name="label">
                <string>type:dia</string>
            </attr>
        </edge>
        <edge from="n9" to="n6">
            <attr name="label">
                <string>total</string>
            </attr>
            <attr name="layout">
                <string>466 10 529 120 661 113 11</string>
            </attr>
        </edge>
        <edge from="n10" to="n10">
            <attr name="label">
                <string>bool:false</string>
            </attr>
        </edge>
        <edge from="n4" to="n4">
            <attr name="label">
                <string>type:mosquito</string>
            </attr>
        </edge>
        <edge from="n4" to="n10">
            <attr name="label">
                <string>voou</string>
            </attr>
        </edge>
        <edge from="n4" to="n1">
            <attr name="label">
                <string>botou</string>
            </attr>
            <attr name="layout">
                <string>951 -29 482 321 567 323 11</string>
            </attr>
        </edge>
        <edge from="n4" to="n5">
            <attr name="label">
                <string>dias_mosquito</string>
            </attr>
            <attr name="layout">
                <string>335 -29 482 321 489 249 11</string>
            </attr>
        </edge>
        <edge from="n4" to="n3">
            <attr name="label">
                <string>nasceu</string>
            </attr>
            <attr name="layout">
                <string>471 -15 482 321 316 324 11</string>
            </attr>
        </edge>
        <edge from="n0" to="n0">
            <attr name="label">
                <string>type:casa</string>
            </attr>
        </edge>
        <edge from="n0" to="n7">
            <attr name="label">
                <string>foco</string>
            </attr>
            <attr name="layout">
                <string>399 -23 168 224 116 129 11</string>
            </attr>
        </edge>
        <edge from="n0" to="n8">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>535 -20 168 224 177 392 11</string>
            </attr>
        </edge>
        <edge from="n0" to="n3">
            <attr name="label">
                <string>voar</string>
            </attr>
            <attr name="layout">
                <string>328 25 316 324 168 224 11</string>
            </attr>
        </edge>
    </graph>
</gxl>
