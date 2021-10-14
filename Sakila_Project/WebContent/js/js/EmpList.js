
var ratings = Ext.create('Ext.data.Store', {
	fields : [ 'rating' ],
	data : [ {
		"rating" : "G"
	}, {
		"rating" : "NC-17"
	}, {
		"rating" : "PG"
	}, {
		"rating" : "PG-13"
	}, {
		"rating" : "R"
	} ]
});

Ext.define('LanguageModel', {
	extend : 'Ext.data.Model',
	fields: ['languageId', 'name'],
});

Ext.define('LanguageStore', {
	extend : "Ext.data.Store",
	model : "LanguageModel",
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : 'fetchotherFilmData?type=language',
		reader : {
			type : 'json',
			rootProperty : 'myData',
		}
	}
});

Ext.define('SpecialFeaturesStore', {
	extend : "Ext.data.Store",
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : 'fetchotherFilmData?type=special_feature',
		reader : {
			type : 'json',
			rootProperty : 'myData',
		}
	}
});

Ext.define('User', {
	extend : 'Ext.data.Model',
	 fields: [
	    	{name: 'title', type:'string'},
	    	{name: 'description', type:'string'},
	    	{name: 'releaseYear', type:'string'},
	    	{name: 'language', type:'string'},
	    	{name: 'director', type:'string'},
	    	{name: 'rating', type:'string'},
	    	{name: 'specialFeatures', type:'string'}
	    ]
});

Ext.define('UserStore', {
	extend : "Ext.data.Store",
	autoLoad : true,
	model : "User",
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : 'fetchFilmData.action',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProperty : 'count',
		}
	}
});


var languageStore = Ext.create('LanguageStore');
var SpecialFeaturesStore = Ext.create('SpecialFeaturesStore');
var activityStore = Ext.create('UserStore');

Ext.onReady(function() {
	advancedSearch();
	grid();
});

function advancedSearch() {
	console.log('Loading Advanced Search UI ...');
	var form = Ext.create('Ext.form.Panel', {
		renderTo : Ext.getBody(),
		xtype : 'form-hboxlayout',
		bodyPadding : '50 0 40 0',
		title : 'Movie Advance Search',
		width : '100%',
		buttonAlign : 'center',
		fieldDefaults : {
			msgTarget : 'side',
			width : 310,
		},
		defaults : {
			border : false,
			xtype : 'panel',
			flex : 1,
			layout : 'anchor'
		},
		layout : {
			type : 'hbox',
			align : 'middle',
			bodyPadding : 50,
			pack : 'center',
		},

		items : [ {
			bodyPadding : '50 150 50 300',
			flex : 5,
			items : [ {
				xtype : 'textfield',
				fieldLabel : 'Movie Name',
				anchor : '-5',
				name : 'title',
				id : 'titleSearch',
			}, {
				xtype : 'datefield',
				fieldLabel : 'Release Year',
				anchor : '-5',
				name : 'releaseYear',
				id : 'releaseYearSearch',
				format : 'Y',
				minValue : new Date(1970),
				maxValue : new Date()
			} ]
		}, {
			bodyPadding : '50 320 50 130',
			flex : 5,
			items : [ {
				xtype : 'textfield',
				fieldLabel : 'Director Name',
				anchor : '100%',
				name : 'director',
				id : 'directorSearch',
			}, {
				xtype : 'combobox',
				id : 'languadeId',
				fieldLabel : 'Language',
				name : 'language',
				store : languageStore,
				valueField : 'languageId',
				displayField : 'name',
				typeAhead : true,
				queryMode : 'remote',
				anchor : '100%',
				id : 'languageSearch',
			} ]
		} ],
		buttonAlign : 'center',
		buttons : [ {
			text : 'Search',
			handler : function() {
				searchData();
			}
		}, {
			text : 'Reset',
			handler : function() {
				resetFields(form);
			}
		} ]
	});
	console.log('Successfully Rendered Loading Advanced Search UI.');
}

function grid() {
	console.log('Loading Grid UI ...');
	dataGrid = Ext.create('Ext.grid.Panel',
			{
				renderTo : Ext.getBody(),
				id : 'grid',
				width : '100%',
				height : 480,
				store : activityStore,
				title : 'Movie Grid',
				columns : [ {
					text : 'Title',
					width : 180,
					sortable : false,
					hidden : true,
					dataIndex : 'filmId'
				}, {
					text : 'Title',
					width : 180,
					sortable : false,
					hideable : false,
					dataIndex : 'title'
				}, {
					text : 'Description',
					width : 550,
					sortable : false,
					hideable : false,
					dataIndex : 'description',
				}, {
					text : 'Release Year',
					width : 100,
					sortable : false,
					hideable : false,
					dataIndex : 'releaseYear',
				}, {
					text : 'Language',
					flex : 1,
					sortable : false,
					hideable : false,
					dataIndex : 'language'
				}, {
					text : 'Director',
					width : 120,
					sortable : false,
					hideable : false,
					dataIndex : 'director',
				}, {
					text : 'Rating',
					width : 150,
					sortable : false,
					hideable : false,
					dataIndex : 'rating',
				}, {
					text : 'Special Features',
					width : 300,
					sortable : false,
					hideable : false,
					dataIndex : 'specialFeatures',
				} ],

				selModel : {
					selType : 'checkboxmodel',
					border : false,
					listeners : {
						select : function(grid, record) {
							record = Ext.getCmp('grid').getSelectionModel().getSelection();
							if (record.length == 1) {
								Ext.getCmp("editbtn").enable();
							} else {
								Ext.getCmp("editbtn").disable();
							}
							if (record.length > 0) {
								Ext.getCmp("deletebtn").enable();
							} else {
								Ext.getCmp("deletebtn").disable();
							}
							data = record;
						},
						deselect : function(grid, record) {
							record = Ext.getCmp('grid').getSelectionModel().getSelection();
							if (record.length == 1) {
								Ext.getCmp("editbtn").enable();
							} else {
								Ext.getCmp("editbtn").disable();
							}
							if (record.length > 0) {
								Ext.getCmp("deletebtn").enable();
							} else {
								Ext.getCmp("deletebtn").disable();
							}
							data = record;
						}
					}
				},
				tbar : new Ext.PagingToolbar(
						{
							inputItemWidth : 50,
							pageSize : 10,
							store : activityStore,
							displayInfo : true,
							displayMsg : 'Displaying topics {0} - {1} of {2}',
							emptyMsg : "No topics to display",
							items : [
								'-',
								{
									iconCls : 'fa-plus-circle opicon',
									text : 'Add',
									handler : function() {
										addData(dataGrid);
									}
								},
								'-',
								{
									iconCls : 'fa-pencil-square-o opicon',
									text : 'Edit',
									id : 'editbtn',
									disabled : true,
									handler : function() {
										editData(data);
									}
								},
								'-',
								{
									text : 'Delete',
									id : 'deletebtn',
									disabled : true,
									handler : function() {
										Ext.Msg
										.show({
											title : 'Are you sure?',
											message : 'Are you sure that you want to delete these records?',
											buttons : Ext.Msg.YESNOCANCEL,
											icon : Ext.Msg.QUESTION,
											fn : function(btn) {
												if (btn === 'yes') {
													deleteData(data);
												}
											}
										});
									}
								}]
						})

			});
	console.log('Successfully Rendered Grid UI.');
}

function searchData() {
	var title = Ext.getCmp('titleSearch').getValue();
	var releaseYear = Ext.getCmp('releaseYearSearch').getValue();
	releaseYear = new Date(releaseYear);
	releaseYear = releaseYear.getFullYear();
	var director = Ext.getCmp('directorSearch').getValue();
	var language = Ext.getCmp('languageSearch').getValue();
	activityStore.getProxy().setUrl(
			'fetchFilmData.action?title=' + title + '&releaseYear='
			+ releaseYear + '&director=' + director + '&language='
			+ language);
	activityStore.load();
}

function resetFields(form) {
	form.getForm().reset();
}


function addData(grid) {
	var form = new Ext.form.Panel(
			{
				url : 'addFilmData.action',
				width : 500,
				title : 'Add Film',
				floating : true,
				closable : true,
				frame : true,
				bodyPadding : 10,
				defaultType : 'textfield',
				items : [ {
					allowBlank : false,
					fieldLabel : 'Title',
					name : 'title',
					msgTarget : 'under',
					width : '100%',
					allowBlank : false,
					id : 'title',
				}, {
					allowBlank : false,
					fieldLabel : 'Release Year',
					msgTarget : 'under',
					name : 'releaseYear',
					xtype : 'numberfield',
					width : '100%',
					minValue : 1,
					id : 'releaseYear',
				}, {
					xtype : 'combobox',
					id : 'specialFeatures',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Special Features',
					store : SpecialFeaturesStore,
					valueField : 'specialFeatures',
					displayField : 'specialFeatures',
					name : 'specialFeatures',
					queryMode : 'local',
					multiSelect : true,
				}, {
					xtype : 'combobox',
					id : 'rating',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Rating',
					store : ratings,
					valueField : 'rating',
					displayField : 'rating',
					name : 'rating',
					typeAhead : true,
					queryMode : 'local',
				}, {
					xtype : 'combobox',
					id : 'languageId',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Language',
					store : languageStore,
					valueField : 'languageId',
					displayField : 'name',
					name : 'language',
					typeAhead : true,
					queryMode : 'local',
				}, {
					fieldLabel : 'Director Name',
					msgTarget : 'under',
					name : 'director',
					width : '100%',
					id : 'director',
				}, {
					allowBlank : false,
					fieldLabel : 'Description',
					name : 'description',
					xtype : 'textareafield',
					maxRows : 4,
					width : '100%',
					id : 'description',
				} ],
				buttonAlign : 'center',
				buttons : [
					{
						text : 'Save',
						disabled : true,
						formBind : true,
						handler : function() {
							var formData = this.up('form').getForm();
							form
							.getForm()
							.submit(
									{
										method : 'POST',
										waitTitle : 'Connecting',
										waitMsg : 'Adding Data...',
										success : function(form,
												action) {
											Ext.Msg
											.alert(
													'Success',
													action.result.successresponse.message);
											form.reset();
											Ext.getCmp('grid')
											.getStore()
											.reload();
										},
										failure : function(form,
												action) {
											Ext.Msg
											.alert(
													'Failure',
													action.result.successresponse.message);
										}
									});
						}
					}, {
						text : 'Cancel',
						handler : function() {
							resetFields(form);
							form.close();
						}
					}

					]
			}).show();
}

function deleteData(record) {
	var filmId = [];
	record.forEach(function(item, index) {
		filmId.push(item.data.filmId);
	});
	filmId = filmId.join();
	Ext.Ajax.request({
		url : 'deleteFilmData.action',
		method : 'POST',
		params : {
			"filmId" : filmId
		},
		success : function(response) {
			var obj = JSON.parse(response.responseText);
			if (obj.success) {
				Ext.Msg.alert('Success', obj.successresponse.message);
				Ext.getCmp('grid').getStore().reload();
				Ext.getCmp("deletebtn").disable();
				Ext.getCmp("editbtn").disable();
			} else {
				Ext.Msg.alert('Failure', obj.successresponse.message);
			}
		},
		failure : function(response) {
			var obj = JSON.parse(response.responseText);
			if (obj.success) {
				Ext.Msg.alert('Success', obj.successresponse.message);
				Ext.getCmp('grid').getStore().reload();
				Ext.getCmp("deletebtn").disable();
				Ext.getCmp("editbtn").disable();
			} else {
				Ext.Msg.alert('Failure', obj.successresponse.message);
			}
		}
	});
}

function editData(record) {
	var specialFeaturesObj = record[0].data.specialFeatures.split(',');
	var languageIdObj = languageStore.findRecord('name',record[0].data.language);
	var languageId = languageIdObj.data.languageId;
	
	var form = new Ext.form.Panel(
			{
				url : 'editFilmData.action',
				width : 500,
				title : 'Edit Film',
				floating : true,
				closable : true,
				frame : true,
				bodyPadding : 10,
				defaultType : 'textfield',
				items : [ {
					allowBlank : false,
					fieldLabel : 'Film ID',
					name : 'filmId',
					width : '100%',
					value : record[0].data.filmId,
					hidden : true
				}, {
					allowBlank : false,
					fieldLabel : 'Title',
					name : 'title',
					msgTarget : 'under',
					width : '100%',
					allowBlank : false,
					id : 'title',
					value : record[0].data.title
				}, {
					allowBlank : false,
					fieldLabel : 'Release Year',
					msgTarget : 'under',
					name : 'releaseYear',
					xtype : 'numberfield',
					width : '100%',
					minValue : 1,
					id : 'releaseYear',
					value : record[0].data.releaseYear
				}, {
					xtype : 'combobox',
					id : 'specialFeatures',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Special Features',
					store : SpecialFeaturesStore,
					valueField : 'specialFeatures',
					displayField : 'specialFeatures',
					name : 'specialFeatures',
					queryMode : 'local',
					multiSelect : true,
					value : specialFeaturesObj
				}, {
					xtype : 'combobox',
					id : 'rating',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Rating',
					store : ratings,
					valueField : 'rating',
					displayField : 'rating',
					name : 'rating',
					typeAhead : true,
					queryMode : 'local',
					value : record[0].data.rating
				}, {
					xtype : 'combobox',
					id : 'languageId',
					allowBlank : false,
					width : '100%',
					fieldLabel : 'Language',
					store : languageStore,
					valueField : 'languageId',
					displayField : 'name',
					name : 'language',
					typeAhead : true,
					queryMode : 'local',
					value : languageId
				}, {
					fieldLabel : 'Director Name',
					msgTarget : 'under',
					name : 'director',
					width : '100%',
					id : 'director',
					value : record[0].data.director
				}, {
					allowBlank : false,
					fieldLabel : 'Description',
					name : 'description',
					xtype : 'textareafield',
					maxRows : 4,
					width : '100%',
					id : 'description',
					value : record[0].data.description
				} ],
				buttonAlign : 'center',
				buttons : [
					{
						text : 'Save',
						disabled : true,
						formBind : true,
						handler : function() {
							form
							.getForm()
							.submit(
									{
										method : 'POST',
										waitTitle : 'Connecting',
										waitMsg : 'Updating Data...',
										success : function(form,action) {
											Ext.Msg
											.alert(
													'Success',
													action.result.successresponse.message);
											Ext.getCmp('grid').getStore()
											.reload();
											Ext.getCmp("editbtn")
											.disable();
											Ext.getCmp("deletebtn")
											.disable();
										},
										failure : function(form,
												action) {
											Ext.Msg
											.alert(
													'Failure',
													action.result.successresponse.message);
										}
									});
						}
					}, {
						text : 'Cancel',
						handler : function() {
							resetFields(form);
							form.close();
						}
					}

					]
			}).show();
}
