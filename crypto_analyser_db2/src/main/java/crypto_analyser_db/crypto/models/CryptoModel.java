package crypto_analyser_db.crypto.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The CryptoModel class represents a cryptocurrency entity in the database.
 * It is annotated with JPA annotations to map the class fields to the respective columns in the "crypto" table.
 */
@Entity
@Table(name="crypto")  // Maps this class to the "crypto" table in the database
public class CryptoModel {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private CryptoCategory category;
	


    // Primary key of the table, auto-generated by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
  

    
    // Represents the rank of the cryptocurrency
    @Column(name = "ranking")
    @JsonProperty("ranking")
    private int rank;

    // Represents the name of the cryptocurrency
    @Column(name = "name_e")
    
    @JsonProperty("name_e")
    private String name;

    // Represents the symbol of the cryptocurrency (e.g., BTC for Bitcoin)
    @Column(name = "symbol")
    private String symbol;

    // Represents the current price of the cryptocurrency
    @Column(name = "price")
    private double price;

    // Represents the percentage change in the price over the last hour
    @Column(name = "ONE_HOUR_CHANGE")  
    @JsonProperty("ONE_HOUR_CHANGE")
    private double oneHourChange;

    // Represents the percentage change in the price over the last 24 hours
    @Column(name = "TWENTY_FOUR_HOUR_CHANGE")  
    
    @JsonProperty("TWENTY_FOUR_HOUR_CHANGE")
    private double twentyFourHourChange;

    // Represents the total market capitalization of the cryptocurrency
    @Column(name = "MARKET_CAP")  
    @JsonProperty("MARKET_CAP")
    private int marketCap;

    // Represents the trading volume of the cryptocurrency
    @Column(name = "volume")
    private long volume;
    
   
    
    
 
    /**
     * Default constructor.
     * Used by JPA for object creation and data population.
     */
    public CryptoModel() {}

    /**
     * Parameterized constructor to create a new CryptoModel object with all attributes.
     *
     * @param ranking               The rank of the cryptocurrency
     * @param name               The name of the cryptocurrency
     * @param symbol             The symbol of the cryptocurrency
     * @param price              The current price of the cryptocurrency
     * @param oneHourChange      Percentage price change in the last hour
     * @param twentyFourHourChange Percentage price change in the last 24 hours
     * @param marketCap          The total market capitalization
     * @param volume             The trading volume of the cryptocurrency
     */
    public CryptoModel(int rank, String name, String symbol, double price, double oneHourChange, double twentyFourHourChange, int marketCap, long volume,CryptoCategory category) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.oneHourChange = oneHourChange;
        this.twentyFourHourChange = twentyFourHourChange;
        this.marketCap = marketCap;
        this.volume = volume;
        this.category = category;
      
    }
    
    

    // Getter and setter methods for each field

    public Long getId() {
        return id;
    }
 
   

    public void setId(Long id) {
        this.id = id;
    }
    
    public CryptoCategory getCategory() {
        return category;
    }

    public void setCategory(CryptoCategory category) {
        this.category = category;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOneHourChange() {
        return oneHourChange;
    }

    public void setOneHourChange(double oneHourChange) {
        this.oneHourChange = oneHourChange;
    }

    public double getTwentyFourHourChange() {
        return twentyFourHourChange;
    }

    public void setTwentyFourHourChange(double twentyFourHourChange) {
        this.twentyFourHourChange = twentyFourHourChange;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(int marketCap) {
        this.marketCap = marketCap;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Overridden toString() method to return the CryptoModel object details in a readable string format.
     * 
     * @return A string containing the details of the CryptoModel object.
     */
    @Override
    public String toString() {
        return "CryptoModel{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", oneHourChange=" + oneHourChange +
                ", twentyFourHourChange=" + twentyFourHourChange +
                ", marketCap=" + marketCap +
                ", volume=" + volume +
                ", catogory" + category +
                '}';
    }

   

	
}
