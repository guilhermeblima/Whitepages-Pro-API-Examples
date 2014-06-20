package com.whitepages.webservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.whitepages.data.AddressLookupData;
import com.whitepages.utilities.AddressLookupParser;
import com.whitepages.utilities.ServerApis;
import com.whitepages.utilities.ServerUrls;
import com.whitepages.utilities.Constants;

public class AddressService {

	public AddressLookupData getAddressLookUpData(String streetLine, String city) throws UnsupportedEncodingException
	{
		AddressLookupData addressLookupData = null;
		
		streetLine = (streetLine != null && !streetLine.equals("")) ? streetLine.trim() : "";
		streetLine = URLEncoder.encode(streetLine, "UTF-8");
		city = (city != null && !city.equals("")) ? city.trim() : "";
		city = URLEncoder.encode(city, "UTF-8");
		
		String request = getAdressLookUpRequest(streetLine, city);
		HttpRestClient httpRestClient = new HttpRestClient();
		String response = httpRestClient.doGetAddresslookupData(request);
		
		// Parse address lookup data json response
		addressLookupData = parseDataResponse(response);
		
		
		return addressLookupData;
	}
	
	/**
	 * This method create a request to get person address lookup data.
	 * @param streetLine: streetLine to get data.
	 * @param city: city to get data.
	 * @return: request to get address lookup data.
	 */
	private String getAdressLookUpRequest(String streetLine, String city)
	{
		String request = null;
		String serverUrl = ServerUrls.SERVER_URL;
		String apiVersion = ServerApis.API_VERSION;
		String addressApi = ServerApis.ADDRESS_API;
		String apiKey = Constants.API_KEY;
		String apiRequestParam = ServerApis.ADDRESS_API_REQUEST_PARAM;
		apiRequestParam = apiRequestParam.replace(":street_line_1", streetLine);
		apiRequestParam = apiRequestParam.replace(":city", city);
		apiRequestParam = apiRequestParam.replace(":api_key", apiKey);
		
		request = serverUrl + apiVersion + addressApi + "?" + apiRequestParam;
		return request;
	}
	
	private AddressLookupData parseDataResponse(String jsonResponse)
	{
		AddressLookupData addressLookupData = null;
		if(jsonResponse != null && !jsonResponse.equals(""))
		{
			AddressLookupParser addressLookupParser = new AddressLookupParser();
			addressLookupData = addressLookupParser.parseAddressLookupData(jsonResponse);
		}
		
		return addressLookupData;
	}
}
