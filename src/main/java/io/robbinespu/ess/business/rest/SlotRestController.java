/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SlotRestController
 * Last modified:  5/29/21, 9:26 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Nodes;
import io.robbinespu.ess.model.Slots;
import io.robbinespu.ess.service.ClassSubjectListService;
import io.robbinespu.ess.service.NodeService;
import io.robbinespu.ess.service.SlotService;
import io.robbinespu.ess.service.SubjectsService;
import io.robbinespu.ess.util.DepthFirstSearch;
import io.robbinespu.ess.util.RestControllerHelper;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SlotRestController extends RestControllerHelper {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;
  private static final Logger logger = LoggerFactory.getLogger(SlotRestController.class);

  SlotService slotService;
  SubjectsService subjectsService;
  ClassSubjectListService classSubjectListService;
  NodeService nodeService;

  @Autowired
  public SlotRestController(
      SlotService slotService,
      SubjectsService subjectsService,
      ClassSubjectListService classSubjectListService,
      NodeService nodeService) {
    super();
    this.slotService = slotService;
    this.subjectsService = subjectsService;
    this.classSubjectListService = classSubjectListService;
    this.nodeService = nodeService;
  }

  /*
  View all status of SLOTS
   */
  @GetMapping(value = "/slots/{formYear}/{subjectId}")
  public ResponseEntity<Map> showSlotForFormYearSubjectId(
      @PathVariable("formYear") int formYear, @PathVariable("subjectId") String subjectId) {
    HashMap<String, Slots> map = new HashMap<>();
    logger.debug(
        "ROB ==> findByFormAndName = {}", subjectsService.findByFormAndName(formYear, subjectId));

    subjectsService
        .findByFormAndName(formYear, subjectId)
        .orElseThrow(() -> new CustomRestException("form and subject is not exist on system"));

    classSubjectListService
        .classSubjectListRepo
        .findBySubjectIdAndFormYear(subjectId, formYear)
        .orElseThrow(() -> new CustomRestException("classSubjectList is not exist on system"));

    Optional<ClassSubjectList> classSubjectListDb =
        classSubjectListService.classSubjectListRepo.findBySubjectIdAndFormYear(
            subjectId, formYear);
    List<Slots> slotsDb = slotService.findByClassSubjectList(classSubjectListDb);
    for (Slots i : slotsDb) {
      map.put(
          i.getName(), i); // honestly, I dont understand my code here, but the JSON out as I wanted
    }
    map.putAll(SendStatusSuccess("Slot available, please check"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  /*
  View the best SLOTS to pick with DFS (if available) just 1 slot
   */
  @GetMapping(value = "/slots/{formYear}/{subjectId}/DFS")
  public ResponseEntity<Map> showSlotForFormYearSubjectIdViaDFS(
      @PathVariable("formYear") int formYear, @PathVariable("subjectId") String subjectId) {
    HashMap<String, String> map = new HashMap<>();

    Optional<ClassSubjectList> classSubjectListDb =
        classSubjectListService.classSubjectListRepo.findBySubjectIdAndFormYear(
            subjectId, formYear);

    // Get all nodes
    List<Nodes> nodesDb = nodeService.nodeRepo.findAll();

    // Some fun with statistics
    logger.debug("we have {} total node stored", nodesDb.size());
    logger.debug(
        "we have {} parent node to process", nodeService.nodeRepo.sizeParentSubject(subjectId));

    // grab parent,child and link!
    DepthFirstSearch d = new DepthFirstSearch();
    HashMap<String, ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> mapDFS;
    mapDFS = new HashMap<String, ArrayList<String>>();

    for (int i = 0; i < nodesDb.size(); i++) {
      logger.debug(
          "lets check the nodes[{}] = ({}, {})",
          i,
          nodesDb.get(i).getParent(),
          nodesDb.get(i).getChild());
      mapDFS = d.link(mapDFS, nodesDb.get(i).getParent(), nodesDb.get(i).getChild());
    }
    path = d.dfs(mapDFS, "X", "G" + classSubjectListDb.get().getGroupSlot());
    d.print(mapDFS, path);

    map.putAll(SendStatusSuccess("Slot available (Picked by DFS), don't forget to book"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @GetMapping(value = "/slots/{formYear}/{subjectId}/DFS/suggest")
  public ResponseEntity<Map> showSlotForFormYearSubjectIdViaDFSsuggest(
      @PathVariable("formYear") int formYear, @PathVariable("subjectId") String subjectId) {
    HashMap<String, String> map = new HashMap<>();

    Optional<ClassSubjectList> classSubjectListDb =
        classSubjectListService.classSubjectListRepo.findBySubjectIdAndFormYear(
            subjectId, formYear);

    // Get all nodes
    List<Nodes> nodesDb = nodeService.nodeRepo.findAll();

    // get related slot
    List<Slots> slotsDb = slotService.findByClassSubjectList(classSubjectListDb);

    // Some fun with statistics
    logger.debug("we have {} total node stored", nodesDb.size());
    logger.debug(
        "we have {} parent node to process", nodeService.nodeRepo.sizeParentSubject(subjectId));

    // grab parent,child and link!
    DepthFirstSearch d = new DepthFirstSearch();
    HashMap<String, ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> mapDFS;
    mapDFS = new HashMap<String, ArrayList<String>>();

    for (int i = 0; i < nodesDb.size(); i++) {
      if (nodesDb.get(i).getSlotId() != null) {
        // logger.debug(" node --> {}",nodesDb.get(i).getSlotId());
        // logger.debug(" slot --> {}",slotsDb.size());
        for (int j = 0; j < slotsDb.size(); j++) {
          // logger.debug("check slot => {} ",slotsDb.get(j).getId());
          if (nodesDb.get(i).getSlotId().equalsIgnoreCase(slotsDb.get(j).getId())) {
            logger.debug(
                "check me => {} / {} ", nodesDb.get(i).getSlotId(), slotsDb.get(j).getId());
            if (!slotsDb.get(j).isBooked() && slotsDb.get(j).isActive()) {
              map.put("slotId", slotsDb.get(j).getId());
              map.put("slot name", slotsDb.get(j).getName());
              break;
            } else {
              logger.error("no slot anymore!");
              map.putAll(SendStatusFailed("No slot available"));
            }
          }
        }
      }
    }

    // map.putAll(SendStatusSuccess("Slot available (Picked by DFS), don't forget to book"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
