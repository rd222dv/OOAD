package utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.MemberCatalog;

public class FileUtil {

	public void saveRegistry(MemberCatalog members, File file) throws Exception { //FIXME make me a class

		JAXBContext context = JAXBContext.newInstance(MemberCatalog.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(members, file);

	}
	
	public MemberCatalog loadRegistry(File file) throws Exception { //FIXME make me a class

		JAXBContext context = JAXBContext.newInstance(MemberCatalog.class);
		Unmarshaller unMarshaller = context.createUnmarshaller();

		return (MemberCatalog) unMarshaller.unmarshal(file);

	}
}