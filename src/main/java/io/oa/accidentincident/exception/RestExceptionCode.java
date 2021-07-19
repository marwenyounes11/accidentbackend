package io.oa.accidentincident.exception;



import org.springframework.http.HttpStatus;

public enum RestExceptionCode {
	
	FAMILY_EXISTANT(HttpStatus.FORBIDDEN, "DCI	existant", false),
	USER_NAME_EXISTANT(HttpStatus.FORBIDDEN, "UserName	existant", false),
	USER__NOT_FOUND(HttpStatus.FORBIDDEN, "UserName	inexistant", false),
	FAMILY_NOT_FOUND(HttpStatus.FORBIDDEN, "Family not found", false),
	TYPE_NOT_FOUND(HttpStatus.FORBIDDEN, "type not found", false),
	DElIVRY_NOT_OPEN(HttpStatus.FORBIDDEN, "Delivry_Not_Open", false),
	LOCATION_NOT_FOUND(HttpStatus.FORBIDDEN, "Emplacement introuvable", false),
	LINES_NUMBER_REQUIRED_IN_PAGINATION(HttpStatus.FORBIDDEN, "le nombre de lignes est requis pour la pagination", false),

    DISTRICT_NOT_FOUND(HttpStatus.FORBIDDEN, "District introuvable", false),
    DCI_EXISTANT(HttpStatus.FORBIDDEN, "DCI	existant", false),
    DCI_NOT_FOUND(HttpStatus.FORBIDDEN, "DCI introuvable", false),
    FORM_NOT_FOUND(HttpStatus.FORBIDDEN, "Form introuvable", false),
    INTERNAL_ORDER_ALREADY_VALIDATE(HttpStatus.FORBIDDEN, "Commande interne est deja validé", false),
    FORM_EXISTANT(HttpStatus.FORBIDDEN, "Form existante", false),
    DISTRIBUTION_UNIT_NOT_FOUND(HttpStatus.FORBIDDEN, "Unite de distribution introuvable", false),
    DISTRIBUTION_UNIT_EXISTANT(HttpStatus.FORBIDDEN, "Unite de distribution existante", false),
    DEPOT_NOT_FOUND(HttpStatus.FORBIDDEN, "Depot introuvable", false),
    ROLE_NOT_FOUND(HttpStatus.FORBIDDEN, "Role introuvable", false),
    DEPOT_EXISTANT(HttpStatus.FORBIDDEN, "Depot existante", false),
    
    
    PRODUCT_ALREADY_USED(HttpStatus.FORBIDDEN, "Produit	utilisé", false),
    MOTIF_ALREADY_USED(HttpStatus.FORBIDDEN, "Motif	utilisé", false),
    DCI_ALREADY_USED(HttpStatus.FORBIDDEN, "DCI	utilisé", false),
    DEPOT_ALREADY_USED(HttpStatus.FORBIDDEN, "DEPOT	utilisé", false),
    DISTRIBUTION_UNIT_ALREADY_USED(HttpStatus.FORBIDDEN, "DCI	utilisé", false),
    DOSAGE_ALREADY_USED(HttpStatus.FORBIDDEN, "DISTRIBUTION_UNIT	utilisé", false),
    FAMILY_ALREADY_USED(HttpStatus.FORBIDDEN, "FAMILY	utilisé", false),
    FORM_ALREADY_USED(HttpStatus.FORBIDDEN, "FORM	utilisé", false),
    PHARMA_CLASS_ALREADY_USED(HttpStatus.FORBIDDEN, " PHARMA_CLASS	utilisé", false),
    PRESENTATION_ALREADY_USED(HttpStatus.FORBIDDEN, "PRESENTATION	utilisé", false),
    TYPE_ALREADY_USED(HttpStatus.FORBIDDEN, "TYPE	utilisé", false),
    
    
	STOCK_NOT_FOUND(HttpStatus.FORBIDDEN, "Stock introuvable", false),
	
	NO_CORRESPONDANCE_TOKEN_USERNAME(HttpStatus.FORBIDDEN, "Pas de correspondance entre username dans la requête et celui dans le token ", false),
	
	INTERNAL_DELIVERY_NOT_FOUND(HttpStatus.FORBIDDEN, "livraison interne introuvable", false),
	INTERNAL_DELIVERY_ALREADY_VALIDATE(HttpStatus.FORBIDDEN, "livraison interne a été validate", false),
	EXTERNAL_DELIVERY_NOT_FOUND(HttpStatus.FORBIDDEN, "livraison externe introuvable", false),
	PRODUCT_NOT_EXISTING_IN_THIS_DELIVERY(HttpStatus.FORBIDDEN, "Produit introuvable dans cette livraison", false),
	
	NO_USER_AUTHORIZED(HttpStatus.UNAUTHORIZED, "no user authorized", false),
	
	NO_USER_DETAILS_TOKEN(HttpStatus.FORBIDDEN, "Pas de détails utilisateur dans le Token", false),
	NO_TOKEN(HttpStatus.FORBIDDEN, "Pas de Token dans l'entete de la requête", false),

	RECEPTION_NOT_FOUND(HttpStatus.FORBIDDEN, "reception introuvable", false),
	STATE_NOT_FOUND(HttpStatus.FORBIDDEN, " Etat introuvable", false),
	
	INTERNAL_ORDER_NOT_FOUND(HttpStatus.FORBIDDEN, "Commande interne introuvable", false),
	INTERNAL_ORDER_ALREADY_VALIDATED(HttpStatus.FORBIDDEN, "Commande interne est deja validé", false),
	INTERNAL_ORDER_ALREADY_DELIVRED(HttpStatus.FORBIDDEN, "Commande interne est deja livré", false),
	EXTERNAL_ORDER_ALREADY_DELIVRED(HttpStatus.FORBIDDEN, "Commande externe est deja livré", false),
	INVENTORY_ALREADY_VALIDATED(HttpStatus.FORBIDDEN, "L'inventaire est deja validé", false),
	INTERNAL_DELIVERY_ALREADY_DELIVRED(HttpStatus.FORBIDDEN, "livraison interne est deja livré", false),
	EXTERNAL_DELIVERY_ALREADY_PARTIALLY_DELIVRED(HttpStatus.FORBIDDEN, "livraison externe est deja livré partiellement", false),
	EXTERNAL_DELIVERY_ALREADY_DELIVRED(HttpStatus.FORBIDDEN, "livraison externe est deja livré ", false),
	RECEPTION_ALREADY_DELIVRED(HttpStatus.FORBIDDEN, "reception est deja livré", false),
	RECEPTION_ALREADY_VALIDATED(HttpStatus.FORBIDDEN, "reception est deja livré", false),
	EXTERNAL_ORDER_ALREADY_VALIDATED(HttpStatus.FORBIDDEN, "Commande externe est deja validé", false),
	EXTERNAL_ORDER_NOT_FOUND(HttpStatus.FORBIDDEN, "Commande  externe interne introuvable", false),
	EXTERNAL_ORDER_LINE__NOT_FOUND(HttpStatus.FORBIDDEN, "Ligne Commande  externe interne introuvable", false),
	INTERNAL_ORDER_LINE_NOT_FOUND(HttpStatus.FORBIDDEN, "Ligne Commande interne introuvable", false),
	
	INTERNAL_ORDER_ALREADY_DELIVERED(HttpStatus.FORBIDDEN, "Commande interne déja livré", false),


	PRODUCT_NOT_FOUND(HttpStatus.FORBIDDEN, "Produit introuvable", false),
	LABEL_EXISTED(HttpStatus.FORBIDDEN, "Label est deja existant", false),
	CODE_PCT_EXISTED(HttpStatus.FORBIDDEN, "CODE_PCT est deja existant", false),
	CODE_A_BARRE_EXISTED(HttpStatus.FORBIDDEN, "CODE_A_BARRE est deja existant", false),
	QUANTITY_NOT_FOUND(HttpStatus.FORBIDDEN, "Quantité introuvable", false),
	
	LINE_PRECRIPTION_NOT_FOUND(HttpStatus.FORBIDDEN, "au moin une ligne d'ordonnance requise", false),

	LINE_DISTRIBUTION_NOT_FOUND(HttpStatus.FORBIDDEN, "au moin une ligne de distribution requise", false),

	LINE_DISTRIBUTION_INVALID(HttpStatus.FORBIDDEN, "ligne de distribution non valide", false),

	DAY_ALREADY_TAKEN(HttpStatus.FORBIDDEN, "la date est déja pris", false),
	DAY_ALREADY_OPEN(HttpStatus.FORBIDDEN, "la date est déja Ouvert", false),
	
	DAY_NOT_FOUND(HttpStatus.FORBIDDEN, "journée introuvable", false),
	MVT_TYPE_NOT_FOUND(HttpStatus.FORBIDDEN, "type Mvt introuvable", false),
	DAYTYPE_ALREADY_TAKEN(HttpStatus.FORBIDDEN, "la Journé Type est déja pris", false),
	DAYTYPE_NOT_FOUND(HttpStatus.FORBIDDEN, "la Journé Type introuvable", false),
	EXISTING_DISTRICT(HttpStatus.FORBIDDEN, "district existant", false),

	PRESCRIPTION_NOT_FOUND(HttpStatus.FORBIDDEN, "ordonnance introuvable", false),

	INCONSISTENT_DATA_STOCK(HttpStatus.FORBIDDEN, "Données non cohérente avec celle du stock", false),
	
	METHOD_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Méthode non trouvée", false),
	
	AUTHORIZATION_REQUIRED(HttpStatus.UNAUTHORIZED, "message.authorization.required", false),
	
	ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "message.access.denied", false),
	
	SERVICE_UNAVAILABLE(HttpStatus.UNAUTHORIZED, "message.service.unavailable", false),
	
	INTERNAL_SERVER_ERROR(HttpStatus.UNAUTHORIZED, "message.server.internal.error", false),
	
	PHARMACLASS_EXISTED(HttpStatus.FORBIDDEN, "PharmaClass Existe Deja", false),
	PHARMACLASS_NOT_FOND(HttpStatus.FORBIDDEN, "PharmaClass n'existe pas", false),
	PRESENTATION_EXISTED(HttpStatus.FORBIDDEN, "Presenatation Existe Deja", false),
	PRESENTATION_NOT_FOUND(HttpStatus.FORBIDDEN, "Presenatation inexistant", false),
	EXEPTIONEL_MOVEMENT_NOT_FOUND(HttpStatus.FORBIDDEN, "Mouvement inexistant", false),
	CUSTOMER_EXISTED(HttpStatus.FORBIDDEN, "client Existe Deja", false),
	DOSSAGE_EXISTED(HttpStatus.FORBIDDEN, "Dossage Existe Deja", false),
	DOSSAGE_NOT_FOND(HttpStatus.FORBIDDEN, "Dossage inexistant", false),
	MOTIF_EXISTED(HttpStatus.FORBIDDEN, "Motif Existe Deja", false),
	MOTIF_NOT_FOND(HttpStatus.FORBIDDEN, "Motif inexistant", false),
	
	TYPE_EXISTED(HttpStatus.FORBIDDEN, "Type Existe Deja", false),
	NON_VALID_FIELDS(HttpStatus.UNAUTHORIZED, "message.non.valid.fields", false),
	ROLE_EXISTANT(HttpStatus.FORBIDDEN, "ROLE	existant", false),
	ROLE_INEXISTANT(HttpStatus.FORBIDDEN, "ROLE	inexistant", false),
	USERNAME_PW_NOT_MATCH(HttpStatus.UNAUTHORIZED, "message.username.pwd.not.match", false),
	USER_NOT_ACTIVATED(HttpStatus.UNAUTHORIZED, "message.user.not.activated", false),
	USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Utilisateur n'est pas creé", false),
	
	
	AGENCY_EXISTANT(HttpStatus.FORBIDDEN, "Agency existant", false);

	private HttpStatus httpStatus;
	private String error;
	private boolean logError;

	RestExceptionCode(HttpStatus httpStatus, String error, boolean logError) {
		this.httpStatus = httpStatus;
		this.error = error;
		this.logError = logError;
	}

	RestExceptionCode(HttpStatus httpStatus, String error, boolean logError, String message) {
		this.httpStatus = httpStatus;
		this.error = error;
		this.logError = logError;
	}

	public static RestExceptionCode fromString(String code) {
		try {
			return RestExceptionCode.valueOf(code.trim().toUpperCase());
		} catch (NullPointerException ex) {
			return null;
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getError() {
		return error;
	}

	public boolean isLogError() {
		return logError;
	}

	public void setError(String error) {
		this.error = error;
	}
}
