package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.services.*;

import java.io.IOException;

public class EnvironmentController {
    @FXML
    private Pane pane;
    private Stage stage;
    private TowerService towerService;
    private InventoryService inventoryService;
    private RandomEventService randomEventService;

    /**
     * Initialize the environment game controller
     */
    public void init(Stage stage) {
        towerService = new TowerService();
        inventoryService = new InventoryService(towerService);
        randomEventService = new RandomEventService(inventoryService);
        this.stage = stage;
        new EnvironmentManager(this::launchSetupScreen, this::launchInventoryScreen, this::launchRoundDifficultySelectScreen, this::launchEasyGameScreen, this::launchWinnerNextRoundScreen, this::launchLoserScreen, this::launchShopScreen, this::launchModerateGameScreen, this::launchChallengingGameScreen, this::launchWinnerGameScreen, this::clearPane);
    }

    public void launchSetupScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupScreenController(environmentManager, this.inventoryService));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Set Up Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchInventoryScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new InventoryController(environmentManager, new ShopService(this.inventoryService), this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Manager Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void launchRoundDifficultySelectScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_difficulty_select_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RoundDifficultySelectController(environmentManager, this.randomEventService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Difficulty Select Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchEasyGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/easy_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new EasyGameController(environmentManager, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Easy Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchWinnerNextRoundScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/winner_next_round_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new WinnerNextRoundController(environmentManager, this.randomEventService, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Winner Next Round Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchLoserScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/loser_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new LoserController(environmentManager, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Loser Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchShopScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopController(environmentManager, new ShopService(this.inventoryService), this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchModerateGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/moderate_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ModerateGameController(environmentManager, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Moderate Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchChallengingGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/challenging_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ChallengingGameController(environmentManager, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Challenging Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchWinnerGameScreen(EnvironmentManager environmentManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/winner_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new WinnerGameController(environmentManager, this.inventoryService));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Winning Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

