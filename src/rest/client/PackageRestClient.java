package rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Package;

public class PackageRestClient {

	private Client client = ClientBuilder.newClient();
	private String mediaType = MediaType.APPLICATION_XML;
	private WebTarget root = client.target("http://localhost:9080/shipping/rest/packages");
	
	public void addPackage(Package pac) {
		Entity<Package> packageEntity = Entity.entity(pac, mediaType);
		Response response = root.request().post(packageEntity, Response.class);
		System.out.println(response.getStatus());
	}
	
	public Package getPackageByTrackingNum(String tracking_num) {
		return root.path(tracking_num).request().get(Response.class).readEntity(Package.class);
	}
	
}
