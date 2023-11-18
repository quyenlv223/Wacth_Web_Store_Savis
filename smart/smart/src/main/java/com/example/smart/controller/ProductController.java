package com.example.smart.controller;

import com.example.smart.dto.respone.attribute.chip.ChipRespone;
import com.example.smart.dto.respone.attribute.loai_os.Loai_OsRespone;
import com.example.smart.dto.respone.attribute.os.OsRespone;
import com.example.smart.dto.respone.attribute.pin.PinRespone;
import com.example.smart.dto.respone.attribute.ram.RamRespone;
import com.example.smart.dto.respone.attribute.rom.RomRespone;
import com.example.smart.dto.respone.attribute.screen.ScreenReposne;
import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.dto.respone.color.ColorRespone;
import com.example.smart.dto.respone.product.ProductResponse;
import com.example.smart.dto.respone.supplier.SupplierResponseDTO;
import com.example.smart.service.*;
import com.example.smart.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    private final ICategoryService categoryService;

    private final ISupplierService supplierService;

    private final SessionUtil sessionUtil;

    private final IColorService colorService;

    private final IScreenService screenService;

    private final IOsService osService;
    private final ILoaiOsService loaiOsService;
    private final IRamService ramService;

    private final IPinService pinService;

    private final IChipService chipService;


    private final IRomValueService IRomValueService;


    @GetMapping()
    public String index(Model model) {

        List<CategoryResponeDto> categoryDTOList = categoryService.getAllCategory();
        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAll();
        List<ProductResponse> productResponseList = productService.findAll();
        List<ColorRespone> colorRespones = colorService.findAll();
        List<ScreenReposne> screenReposnes = screenService.findAllScreen();
        List<OsRespone> osRespones = osService.findAll();
        List<PinRespone> pinRespones = pinService.findAll();
        List<RamRespone> ramRespones = ramService.findAll();
        List<ChipRespone> chipRespones = chipService.findAll();
        List<Loai_OsRespone> loaiosRespones = loaiOsService.findAll();

        List<RomRespone> romRespones = IRomValueService.findAll();
        model.addAttribute("listRom", romRespones);
        model.addAttribute("listChip", chipRespones);
        model.addAttribute("listCategory", categoryDTOList);
        model.addAttribute("listSupplier", supplierResponseDTOS);
        model.addAttribute("listProduct", productResponseList);
        model.addAttribute("listColor", colorRespones);
        model.addAttribute("listScreen", screenReposnes);
        model.addAttribute("listOs", osRespones);
        model.addAttribute("listRam", ramRespones);
        model.addAttribute("listPin", pinRespones);
        model.addAttribute("listloaios",loaiosRespones);
        return "/views/product/product";
    }

}
