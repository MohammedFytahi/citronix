    package com.example.Citronix.service.impl;

    import com.example.Citronix.dto.tree.TreeCreateDTO;
    import com.example.Citronix.dto.tree.TreeDTO;
    import com.example.Citronix.dto.tree.TreeUpdateDTO;
    import com.example.Citronix.mapper.TreeMapper;
    import com.example.Citronix.model.Field;
    import com.example.Citronix.model.Tree;
    import com.example.Citronix.repository.FieldRepository;
    import com.example.Citronix.repository.TreeRepository;
    import com.example.Citronix.service.interf.TreeServiceInterface;
    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
     import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;
    @Service
    @RequiredArgsConstructor
    public class TreeService implements TreeServiceInterface {

         private final TreeRepository treeRepository;

         private final FieldRepository fieldRepository;

         private final  TreeMapper treeMapper;

        private static final int MAX_TREES_PER_HECTARE = 100;
        private static final int MAX_TREES_PER_1000M2 = 10;

        private static final int MAX_TREE_AGE = 20;  

        public void createTree(TreeCreateDTO treeCreateDTO) {
             if (!Tree.isPlantingDateValid(treeCreateDTO.getPlantingDate())) {
                throw new IllegalArgumentException("Les arbres ne peuvent être plantés qu'entre mars et mai.");
            }

            Field field = fieldRepository.findById(treeCreateDTO.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found for ID: " + treeCreateDTO.getFieldId()));

            double totalFieldArea = field.getArea();
            long numberOfTreesInField = treeRepository.countByFieldId(field.getId());

             if (numberOfTreesInField >= (totalFieldArea / 1000) * MAX_TREES_PER_1000M2) {
                throw new IllegalArgumentException("La densité des arbres dans ce champ dépasse la limite autorisée de " + MAX_TREES_PER_1000M2 + " arbres pour 1000 m².");
            }

            Tree tree = TreeMapper.INSTANCE.toEntity(treeCreateDTO);
            tree.setField(field);

             if (tree.calculateAge() > MAX_TREE_AGE) {
                throw new IllegalArgumentException("L'arbre ne peut pas être productif au-delà de " + MAX_TREE_AGE + " ans.");
            }

            treeRepository.save(tree);
        }

        public List<Tree> getTreesByField(Long fieldId) {
            return treeRepository.findByFieldId(fieldId);
        }

        public double calculateTotalProductivity(Long fieldId) {
            List<Tree> trees = getTreesByField(fieldId);
            return trees.stream().mapToDouble(Tree::calculateAnnualProductivity).sum();
        }

        public List<TreeDTO> getAllTreesWithAge() {
            List<Tree> trees = treeRepository.findAll();
            return trees.stream()
                    .map(TreeMapper.INSTANCE::toResponseDTO)
                    .collect(Collectors.toList());
        }

        @Transactional
        public Tree updateTree(Long id, TreeUpdateDTO treeUpdateDTO) {
             Tree existingTree = treeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + id));

             if (treeUpdateDTO.getPlantingDate() != null && !Tree.isPlantingDateValid(treeUpdateDTO.getPlantingDate())) {
                throw new IllegalArgumentException("Les arbres ne peuvent être plantés qu'entre mars et mai.");
            }

             if (treeUpdateDTO.getPlantingDate() != null) {
                existingTree.setPlantingDate(treeUpdateDTO.getPlantingDate());
            }

             if (treeUpdateDTO.getFieldId() != null) {
                Field newField = fieldRepository.findById(treeUpdateDTO.getFieldId())
                        .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + treeUpdateDTO.getFieldId()));
                existingTree.setField(newField);
            }

             return treeRepository.save(existingTree);
        }
    }
