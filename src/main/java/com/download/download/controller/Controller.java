package com.download.download.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.download.download.combo.ResponseFile;
import com.download.download.combo.TSapplication;
import com.download.download.combo.application;
import com.download.download.combo.controllerApplication;
import com.download.download.combo.htmlApplication;
import com.download.download.combo.repoApplication;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Controller {
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get/{method}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<ByteArrayResource> run(@PathVariable String method) throws Exception {

		ResponseFile response = null;// application.main(null);

		switch (method) {

		case "entity":
			response = application.main(null);
			break;

		case "repo":
			response = repoApplication.main(null);
			break;

		case "controller":
			response = controllerApplication.main(null);
			break;

		case "html":
			response = htmlApplication.main(null);
			break;

		case "ts":
			response = TSapplication.main(null);
			break;
		}

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", response.getName()));
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		ByteArrayResource resource = new ByteArrayResource(response.getContents().getBytes());

		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
}
