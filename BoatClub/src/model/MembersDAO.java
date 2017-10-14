package model;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MembersDAO {

	/**
	 * Saves information to database.xml
	 * @param members MemberCatalog object
	 * @param file File to write to
	 * @throws Exception
	 */
	public void saveRegistry(MemberCatalog members, File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(MemberCatalog.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(members, file);
	}
	
	/**
	 * Loads information from database.xml, loads everything to MemberCatalog object.
	 * @param file File to load from
	 * @return MemberCatalog object with information from database.xml file
	 * @throws Exception
	 */
	public MemberCatalog loadRegistry(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(MemberCatalog.class);
		Unmarshaller unMarshaller = context.createUnmarshaller();
		return (MemberCatalog) unMarshaller.unmarshal(file);
	}
}