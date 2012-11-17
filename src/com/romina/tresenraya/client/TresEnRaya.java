package com.romina.tresenraya.client;

import java.util.Vector;
import com.romina.tresenraya.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TresEnRaya implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		
		
		//Boton para iniciar jugada
		final Button playButton = new Button("Play");
		
		//Campos para poner X o O
		final TextBox p00 = new TextBox();
		final TextBox p01 = new TextBox();
		final TextBox p02 = new TextBox();
		final TextBox p10 = new TextBox();
		final TextBox p11 = new TextBox();
		final TextBox p12 = new TextBox();
		final TextBox p20 = new TextBox();
		final TextBox p21 = new TextBox();
		final TextBox p22 = new TextBox();
		
		 final Vector<TextBox> celdas=new Vector<TextBox>();
		 celdas.add(p00);
		 celdas.add(p01);
		 celdas.add(p02);
		 celdas.add(p10);
		 celdas.add(p11);
		 celdas.add(p12);
		 celdas.add(p20);
		 celdas.add(p21);
		 celdas.add(p22);
		 
		//Label que muestra un error
		final Label errorLabel = new Label();
		
		// We can add style names to widgets
		playButton.addStyleName("playButton");
		p00.addStyleName("00");
		p01.addStyleName("01");
		p02.addStyleName("02");
		p10.addStyleName("10");
		p11.addStyleName("11");
		p12.addStyleName("12");;
		p20.addStyleName("20");
		p21.addStyleName("21");
		p22.addStyleName("22");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		RootPanel.get("playButtonContainer").add(playButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		
		RootPanel.get("p00").add(p00);
		RootPanel.get("p01").add(p01);
		RootPanel.get("p02").add(p02);
		RootPanel.get("p10").add(p10);
		RootPanel.get("p11").add(p11);
		RootPanel.get("p12").add(p12);
		RootPanel.get("p20").add(p20);
		RootPanel.get("p21").add(p21);
		RootPanel.get("p22").add(p22);	
		
		// Focus the cursor on the name field when the app loads
		p00.setFocus(true);
		p00.selectAll();

		
		//Crear un pop-up para avisar que ganaste
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Ganaste el Juego");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Cerrar");
		
		//Podemos establecer el id de un widget accesando a su elemento
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Ganaste Campeon:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		
		
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				playButton.setEnabled(true);
				playButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendPlay();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendPlay();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */

			private void sendPlay() {
				// First, we validate the input.
				errorLabel.setText("");
				Validador val=new Validador();
				val.llenarJugadas();	
								
				if (!val.validarCaracter(celdas)) {
					errorLabel.setText("Solo se puede poner X o O "); 
					return;
				}
				
				else{
					if(val.verificarJugadaGanadora(celdas)){
						playButton.setEnabled(false);
						textToServerLabel.setText("Gracias por Jugar");
						dialogBox.setText("Felicitaciones!!!");
						dialogBox.center();
						closeButton.setFocus(true);
						
						
						for(int i=0;i<celdas.size();i++){
							
							celdas.get(i).setEnabled(true);
							celdas.get(i).setText("");
						}
					}
					
			
				}

						
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		playButton.addClickHandler(handler);
		p00.addKeyUpHandler(handler);
		p01.addKeyUpHandler(handler);
		p02.addKeyUpHandler(handler);
		p10.addKeyUpHandler(handler);
		p11.addKeyUpHandler(handler);
		p12.addKeyUpHandler(handler);
		p20.addKeyUpHandler(handler);
		p21.addKeyUpHandler(handler);
		p22.addKeyUpHandler(handler);
	}
}
