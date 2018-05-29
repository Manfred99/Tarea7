package data;

import domain.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XMLPersonManager {

    //variables
    private Document documen;
    private Element root;//jroom
    private String path;

    public XMLPersonManager(String path) throws JDOMException, IOException {
        this.path = path;

        File fileStudent = new File(path);
        if (fileStudent.exists()) {
            //1. El archivo ya existe, entonces lo cargo en memoria

            //toma la estuctura de datos y las cara en menoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            //cargar en memoria
            this.documen = saxBuilder.build(this.path);
            this.root = this.documen.getRootElement();
        } else {
            //2. El archivo no existe, lo creo y lo guardo

            //creamos en elemento raiz
            this.root = new Element("persons");
            //creamos el documento
            this.documen = new Document(this.root);
            //guardar el archivo en disco duro
            storeXML();
        }
    }//end builder

    private void storeXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.documen, new PrintWriter(this.path));
    }//end method

    public void insertPerson(Person person) throws IOException {
        //debemos crear elemento por elemento
        int contaB = 0;
        int contaP = 0;
        Person[] array = getAllPersons();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getParent_id().equalsIgnoreCase(person.getParent_id())) {
                contaB++;
            }
        }

        if (contaB <= 9) {
            Element ePerson = new Element("person");
            ePerson.setAttribute("id", person.getId());

            //nombre
            Element eName = new Element("name");
            eName.addContent(person.getName());

            //admissiongrade
            Element eLastName1 = new Element("lastName1");
            eLastName1.addContent(person.getLastName1());

            Element eLastName2 = new Element("lastName2");
            eLastName2.addContent(person.getLastName2());

            Element eDate_birth = new Element("dateBirth");
            eDate_birth.addContent(person.getDate_birth());

            Element eCountry = new Element("country");
            eCountry.addContent(person.getCountry());

            Element eParentId = new Element("parent_id");
            eParentId.addContent(person.getParent_id());

            //agregar el elemento student el name y el admissionGrade
            ePerson.addContent(eName);
            ePerson.addContent(eLastName1);
            ePerson.addContent(eLastName2);
            ePerson.addContent(eDate_birth);
            ePerson.addContent(eCountry);
            ePerson.addContent(eParentId);
            //agregamos al root

            //ePerson.setContent(ePerson);
            this.root.addContent(ePerson);

            //guardar el disco duro
            storeXML();
        }else
            System.err.println("Can't insert person");
    }//end method

    //leer todos los hijos de la raiz
    public Person[] getAllPersons() {
        int personQuantity = this.root.getContentSize();

        //obtenemos una lista con todos los elementos de root
        List elementList = this.root.getChildren();

        // definir el tamaño del arreglo
        Person[] personArray = new Person[personQuantity];

        //recorrer la lista para ir insertando en el arreglo
        int count = 0;
        for (Object currentObject : elementList) {
            //castig de object a element
            Element currentElement = (Element) currentObject;

            //crear estudiante
            Person currentPerson = new Person();

            //id
            currentPerson.setId(
                    currentElement.getAttributeValue("id")
            );

            //name
            currentPerson.setName(
                    currentElement.getChild("name").getValue()
            );

            //grade
            currentPerson.setLastName1(
                    currentElement.getChild("lastName1").getValue()
            );

            currentPerson.setLastName2(
                    currentElement.getChild("lastName2").getValue()
            );

            currentPerson.setDate_birth(
                    currentElement.getChild("dateBirth").getValue()
            );

            currentPerson.setCountry(
                    currentElement.getChild("country").getValue()
            );

            currentPerson.setParent_id(
                    currentElement.getChild("parent_id").getValue()
            );

            personArray[count++] = currentPerson;
        }
        return personArray;
    }
    
    public void deletePerson(String id) throws IOException{
        int pos=0;
        Person[] array = getAllPersons();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getId().equalsIgnoreCase(id)) {
                pos=i;
            }
        }
        List elementList = this.root.getChildren();
        elementList.remove(pos);
        
        //finalmente: guardar en DD
        storeXML();
    }
    
    public Person getChild(Person p){
        Person[] array = getAllPersons();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getParent_id().equalsIgnoreCase(p.getId())) {
               return array[i];
            }
        }
        return null;
    }

}
