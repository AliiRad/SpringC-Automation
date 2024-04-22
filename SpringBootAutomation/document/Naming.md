
-----------------------
#.Lombok annots:

	@SuperBuilder
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	

-----------------------
     
#.JPA Entity Annotations :

     @Entity(name = "classNameEntity")
     @Table(name = "className_tbl")
-----------------------

#.Field Annotations:

    @Column(name="classNmae_fieldName" , *length=x , *nullable = false , *unique = true , columnDefinition = "NVARCHAR2(x)" , columnDefinition = "NUMBER(x)")


-----------------------
#.For enum fields:

    @Enumerated(EnumType.ORDINAL)
-----------------------
#.Validation Constraints if needed:

	@NotBlank(message ="Should Not Be Blank")
	@Past(message = "Invalid Start Date")
   	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,30}$", message = "Invalid Name")
	@Pattern(regexp = "^\\d{11}$", message = "only use english numbers")
	@Size(min = 5, max = 200, message = "Job Description must be between 5 and 200 characters")


### *IMPORTANT: All Validation Annoations Must Have a Message in case of Wrong Entry.

-----------------------
#### "Id" Should Be Long.
#### every entity should have 'deleted' field
#### all entities have @OneToMany relation with Attachment
#### no unused import  is allowed .

-----------------------
#. deleted field :
         
      private Boolean deleted = false;


      when record has not been removed    ------>      deleted  = false 
      after deletion 	              ------>      deleted = true


-----------------------

#.sequence :

    	@Id
    	@SequenceGenerator(name = "classNameSeq", sequenceName = "className_seq", initialValue = 1, allocationSize = 1)
    	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classNmaeSeq")

-----------------------


#.we use Constructor Injection instead of Field Injection:

1. in controller:

        private final ClassNameService service;

        @Autowired
        public ClassNameController(ClassNameService service) {
             this.service = service;
        }

**********
2. in service:

       private final ClassNameRepository repository;

       @Autowired
       public ClassNameService(ClassNameRepository repository) {
            this.repository = repository;
       }



-----------------------
#.We use @JoinColumn Instead of @Column , upon relationship fields


       @JoinColumn(name = "job_personId")
       private Person person;

-----------------------
#.we define cascade type and fetch type like this:
    

     @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)

-----------------------

#.we use mappedBy when we have two-way one-to-many relation , like this :


       @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "person")
       private List<Jobs> jobs;


-----------------------
### #.Methods:
1. save()
2. update()
3. logicalRemove()
4. findAllDeletedFalse()
5. findByIdAndDeletedFalse()
6. findById()
7. findAll()
8. CUSTUME FINDS

-----------------------
#.In Controller:


    @RequestMapping("/className")

-----------------------

    save method   ---> @PostMapping("/save")

    edite method  ---> @PutMapping("/edit")

    delete method ---> @DeleteMapping("/delete")

    All Finds :@GetMapping


find with entry :

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id, ...){...}

-----------------------

