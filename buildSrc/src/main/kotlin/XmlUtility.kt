
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.xml.sax.InputSource

/*
 * © 2021-2021 SorrowBlue.
 */

object XmlUtility {


    fun mergeSaveXml(orgXml: String, newXml: String, filePath: String) {

        val finalDocument = mergeXml(orgXml, newXml)

        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val domSource = DOMSource(finalDocument)
        val streamResult = StreamResult(File(filePath))
        transformer.transform(domSource, streamResult)
    }

    fun mergeXml(originalContent: String, newContent: String): Document {
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()

        val xmlOrgInput = InputSource(StringReader(originalContent))
        val xmlOrgDoc = dBuilder.parse(xmlOrgInput)
        val xmlOrgEle = xmlOrgDoc.documentElement

        val xmlNewInput = InputSource(StringReader(newContent))
        val xmlNewgDoc = dBuilder.parse(xmlNewInput)
        val xmlNewEle = xmlNewgDoc.documentElement
        val xmlNewNde = xmlNewEle.childNodes

        for (i in 0 until xmlNewNde.length) {
            val xmlNode = xmlNewNde.item(i)
            println("$xmlNode")
            if (xmlNode.nodeType == Node.ELEMENT_NODE) {
                println("$xmlNode")
                val xmlImportedNode = xmlOrgDoc.importNode(xmlNode, true)
                xmlOrgEle.appendChild(xmlImportedNode)
            }
        }
        return xmlOrgDoc
    }
}