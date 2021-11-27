package com.maruli.sofco.controller;

import com.maruli.sofco.entity.Item;
import com.maruli.sofco.entity.User;
import com.maruli.sofco.object.AddItem;
import com.maruli.sofco.object.BasicResponse;
import com.maruli.sofco.object.BuyItem;
import com.maruli.sofco.object.TranscationResponse;
import com.maruli.sofco.service.ItemService;
import com.maruli.sofco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @GetMapping("items")
    public ResponseEntity<List<Item>> itemList(){
        return ResponseEntity.ok(itemService.itemList());
    }

    @PostMapping("/add-item")
    public ResponseEntity<BasicResponse> addItem(@RequestHeader String Authorization,
                                                 @RequestBody AddItem addItem,
                                                 Principal principal) {
        try {
            itemService.createOrUpdateItem(addItem);
            return ResponseEntity.ok(new BasicResponse(true, "Item " + addItem.getName() + " has been added"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new BasicResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/edit-stock-admin")
    public ResponseEntity<BasicResponse> editStockByAdmin(@RequestHeader String Authorization,
                                                          @RequestParam Long id,
                                                          @RequestParam Integer stock,
                                                          Principal principal) {
        try {
            Boolean isAdmin = userService.isAdmin(principal.getName());
            if (isAdmin) {
                if (itemService.updateStockItem(id, stock)) {
                    return ResponseEntity.ok(new BasicResponse(true, "Item stock has been updated"));
                }
                return ResponseEntity.ok(new BasicResponse(false, "Item not found"));
            }
            return ResponseEntity.badRequest().body(new BasicResponse(false, "You are not admin"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new BasicResponse(false, "Internal Server Error"));
        }
    }

    @PostMapping("/delete-item-admin")
    public ResponseEntity<BasicResponse> deleteItem(@RequestHeader String Authorization,
                                                    @RequestParam Long id,
                                                    Principal principal) {
        try {
            Boolean isAdmin = userService.isAdmin(principal.getName());
            if (isAdmin) {
                itemService.deleteItem(id);
                return ResponseEntity.ok(new BasicResponse(true, "Item has been deleted"));
            }
            return ResponseEntity.badRequest().body(new BasicResponse(false, "You are not admin"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new BasicResponse(false, "Error"));
        }
    }

    @PostMapping("buy-item")
    public ResponseEntity<TranscationResponse> buyItem(@RequestHeader String Authorization,
                                                 @RequestParam String note,
                                                 @RequestBody List<BuyItem> buyItemList,
                                                 Principal principal) {
        try {
            String code = itemService.buyItem(buyItemList, principal, note);
            return ResponseEntity.ok(new TranscationResponse(true,code,""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new TranscationResponse(false,"-",e.getMessage()));
        }
    }

}
